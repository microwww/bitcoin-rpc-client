package com.github.microwww.bitcoin;

import okhttp3.Response;

public class HttpException extends RuntimeException {
    Response response;

    public HttpException(Response response, String format, Object... param) {
        this(response, null, format, param);
    }

    public HttpException(Response response, Throwable cause, String format, Object... param) {
        super(String.format(format, param), cause);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
