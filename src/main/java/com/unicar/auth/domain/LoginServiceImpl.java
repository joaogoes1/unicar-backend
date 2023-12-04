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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServiceImpl implements LoginService {

    public static final int MIN_COST_FACTOR = 4;
    // TODO: COLOCAR A SECRET AQUI
    private static final String SECRET = "gsconfugaogfaonegfoauegfonuayge0f8nctaw4078nrt0n783tr-817q30gryfquk";
    private static final Algorithm algorithm = Algorithm.HMAC512(SECRET);
    private static final Duration TOKEN_TTL = Duration.ofDays(30);
    private static final String JWT_ISSUER = "unicar-spark";
    private final Clock clock;
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

    public static boolean verifyToken(String token) {
        DecodedJWT decodedJWT = null;
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
        }
        return decodedJWT != null;
    }

    public static boolean isValidEmail(String emailStr) {
        final Pattern validEmailPattern =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = validEmailPattern.matcher(emailStr);
        return matcher.matches();
    }

    public static boolean isPucCampinasDomain(String emailStr) {
        final String domain = emailStr.substring(emailStr.indexOf("@") + 1);
        return Objects.equals(domain, "puc-campinas.edu.br") || Objects.equals(domain, "puccampinas.edu.br");
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

    private String generateToken(String email, String userId) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim("userId", userId)
                    .withClaim("email", email)
                    .withExpiresAt(new Date(clock.instant().plus(TOKEN_TTL).toEpochMilli()))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            Logger.error(exception.getMessage());
        }
        return token;
    }

    public String verifyUser(String email, String password) {
        final User user = authDataSource.getUserByEmail(email);
        if (user != null) {
            final String hash = user.getPassword();
            if (verifyPassword(hash, password)) {
                return generateToken(email, user.getUserId());
            }
        }
        return null;
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
    public void registerUser(String email, String password, String name, String phone, String ra) throws InterruptedIOException, DomainNotAllowedException {
        if (email == null || !isValidEmail(email)) throw new IllegalArgumentException("Email inválido");
        if (!isPucCampinasDomain(email)) throw new DomainNotAllowedException();
        authDataSource.createUser(email, hash(password), name, phone, ra);
    }
}
