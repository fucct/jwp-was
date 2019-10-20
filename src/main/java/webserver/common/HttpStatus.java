package webserver.common;

import java.util.Arrays;

public enum HttpStatus {
    OK("200", "OK"),
    FOUND("302", "Found"),
    BAD_REQUEST("400", "Bad Request"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error");

    private final String code;
    private final String message;

    HttpStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpStatus valueOfCode(String code) {
        return Arrays.stream(values()).filter(httpStatus -> code.equals(httpStatus.getCode()))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}