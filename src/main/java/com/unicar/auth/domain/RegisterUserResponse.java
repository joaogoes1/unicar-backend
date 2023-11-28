package com.unicar.auth.domain;

public sealed interface RegisterUserResponse {
    record Success() implements RegisterUserResponse {}
    record InvalidOrganization(String message) implements RegisterUserResponse {}
    record InvalidPassword(String message) implements RegisterUserResponse {}

    record UserAlreadyExistsPassword(String message) implements RegisterUserResponse {}
}
