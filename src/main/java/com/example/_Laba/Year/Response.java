package com.example._Laba.Year;

import java.util.Objects;

public record Response(String response, int days) {
    public Response {
        Objects.requireNonNull(response);
    }

    public static Response createEmpty() {
        return new Response("", 0);
    }

    public Response() {
        this("", 0);
    }
}