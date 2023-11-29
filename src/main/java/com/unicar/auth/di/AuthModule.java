package com.unicar.auth.di;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.unicar.auth.api.LoginController;
import com.unicar.auth.data.datasource.AuthDataSource;
import com.unicar.auth.data.datasource.FirestoreAuthDataSource;
import com.unicar.auth.domain.LoginService;
import com.unicar.auth.domain.LoginServiceImpl;

import java.time.Clock;


public class AuthModule extends AbstractModule {

    @Provides
    static Clock provideClock() {
        return Clock.systemUTC();
    }

    @Provides
    static LoginController provideLoginController(LoginService loginService) {
        return new LoginController(loginService);
    }

    @Provides
    static LoginServiceImpl providesLoginService(Clock clock, AuthDataSource authDataSource) {
        return new LoginServiceImpl(clock, BCrypt.MIN_COST, authDataSource);
    }

    @Override
    protected void configure() {
        bind(LoginService.class).to(LoginServiceImpl.class);
        bind(AuthDataSource.class).to(FirestoreAuthDataSource.class);
    }
}
