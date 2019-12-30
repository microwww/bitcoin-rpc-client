package com.github.microwww.bitcoin;

import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpException extends RuntimeException {
    Response response;
    private static final Logger logger = LoggerFactory.getLogger(HttpException.class);

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

    public void logger() {
        String str;
        try {
            str = response.body().string();
        } catch (IOException e) {
            str = "response.body().string(), IOException :: " + e.getMessage();
        }
        Request request = response.request();
        logger.info("Request TRANCE:: POST {}\n=== header ===\n{}=== body ===\n{}\nResponse TRANCE::\n=== header ===\n{}=== body ===\n{}",
                request.url(), //
                request.headers(), request.body(), //
                response.headers(), str);
    }
}
