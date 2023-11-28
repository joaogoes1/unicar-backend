package com.unicar.profile.domain.response;

public sealed interface RegisterCarResponse {
    record Success() implements RegisterCarResponse {}
    record Error() implements RegisterCarResponse {}
}
