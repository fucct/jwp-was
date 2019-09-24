package webserver.support;

import webserver.exception.NotMatchUrlException;

public class PathHandler {
    private static final String TEMPLATE_URL_FORMAT = "./templates%s";
    private static final String STATIC_URL_FORMAT = "./static%s";
    private static final String REGEX_FOR_TEMPLATES = "/?[A-Za-z0-9/.\\-]+\\.(html|ico)";
    private static final String REGEX_FOR_STATIC = "/?[A-Za-z0-9/.\\-]+\\.(css|js|ttf|woff)";

    public static String path(String url) {
        if (url.matches(REGEX_FOR_TEMPLATES)) {
            return String.format(TEMPLATE_URL_FORMAT, url);
        }

        if (url.matches(REGEX_FOR_STATIC)) {
            return String.format(STATIC_URL_FORMAT, url);
        }

        throw new NotMatchUrlException();
    }
}