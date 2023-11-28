package com.unicar.auth.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.inject.Inject;
import com.unicar.auth.data.datasource.AuthDataSource;
import com.unicar.auth.data.model.User;
import com.unicar.util.log.Logger;

import java.io.InterruptedIOException;
import java.time.Clock;
import java.time.Duration;
import java.util.Date;

public class LoginServiceImpl implements LoginService {

    // TODO: COLOCAR A SECRET AQUI
    private static final String SECRET = "gsconfugaogfaonegfoauegfonuayge0f8nctaw4078nrt0n783tr-817q30gryfquk";
    private static final Algorithm algorithm = Algorithm.HMAC512(SECRET);
    private static final Duration TOKEN_TTL = Duration.ofDays(30);
    private static final String JWT_ISSUER = "unicar-spark";
    private final Clock clock;

    public static final int MIN_COST_FACTOR = 4;
    private final BCrypt.Hasher hasher = BCrypt.with(LongPasswordStrategies.hashSha512(BCrypt.Version.VERSION_2A));
    private final BCrypt.Verifyer verifyer = BCrypt.verifyer();
    private final AuthDataSource authDataSource;
    private int bcryptCostFactor;

    @Inject
    public LoginServiceImpl(Clock clock, int bcryptCostFactor, AuthDataSource authDataSource) {
        assert (bcryptCostFactor >= MIN_COST_FACTOR) : "bcryptCostFactor should be at least " + MIN_COST_FACTOR;
        assert (bcryptCostFactor >= BCrypt.MIN_COST) : "bcryptCostFactor should be at least " + BCrypt.MIN_COST;
        assert (bcryptCostFactor <= BCrypt.MAX_COST) : "bcryptCostFactor should be at max " + BCrypt.MAX_COST;
        this.clock = clock;
        this.authDataSource = authDataSource;
        this.bcryptCostFactor = bcryptCostFactor;
    }

    public String hash(String password) {
        return hasher.hashToString(bcryptCostFactor, password.toCharArray());
    }

    public boolean verifyPassword(String hash, String password) {
        final BCrypt.Result result = verifyer.verify(password.toCharArray(), hash);
        if (!result.verified) {
            Logger.error("Hash verification failed. Error details: " + result.formatErrorMessage);
        }

        return result.verified;
    }


    public static boolean verifyToken(String token) {
        DecodedJWT decodedJWT = null;
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
        }
        return decodedJWT != null;
    }

    private String generateToken(String name, String email, String userId) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim("name", name)
                    .withClaim("userId", userId)
                    .withClaim("email", email)
                    .withExpiresAt(new Date(clock.instant().plus(TOKEN_TTL).toEpochMilli()))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            Logger.error(exception.getMessage());
        }
        return token;
    }

    public VerifyUserResponse verifyUser(String email, String password) {
        final User user = authDataSource.getUserByEmail(email);
        if (user != null) {
            final String hash = user.getPassword();
            if (verifyPassword(hash, password)) {
                final String token = generateToken("name", email, user.getUserId());
                return new VerifyUserResponse.Success(token);
            }
        }
        return new VerifyUserResponse.Failure("Invalid credentials");
    }

    public String getUserIdFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();
            return verifier
                    .verify(token)
                    .getClaim("userId")
                    .asString();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return null;
        }
    }

    @Override
    public RegisterUserResponse registerUser(String email, String password) throws InterruptedIOException {
        try {
            authDataSource.createUser(email, hash(password));
            return new RegisterUserResponse.Success();
        } catch (Exception e) {
            return new RegisterUserResponse.UserAlreadyExistsPassword(e.getMessage());
        }
    }
}
