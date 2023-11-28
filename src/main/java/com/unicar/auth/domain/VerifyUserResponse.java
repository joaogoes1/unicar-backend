package com.unicar.auth.domain;

sealed public interface VerifyUserResponse {
    record Success(String token) implements VerifyUserResponse {
    }

    record Failure(String message) implements VerifyUserResponse {
    }
}
