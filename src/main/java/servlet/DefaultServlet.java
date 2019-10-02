package servlet;

import http.request.HttpRequest;
import http.response.HttpResponse;
import utils.FileIoUtils;
import view.StaticView;
import view.View;

import java.util.ArrayList;
import java.util.List;

import static http.response.HttpStatus.NOT_FOUND;

public class DefaultServlet implements Servlet {
    private static final List<String> FILE_PATH_PREFIXES;

    static {
        FILE_PATH_PREFIXES = new ArrayList<>();
        FILE_PATH_PREFIXES.add("./static");
        FILE_PATH_PREFIXES.add("./templates");
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        for (String prefix : FILE_PATH_PREFIXES) {
            String staticFilePath = prefix + request.getUri().getPath();
            if (FileIoUtils.existFileInClasspath(staticFilePath)) {
                View view = new StaticView(staticFilePath);
                response.sendResponseWithView(view, request.getMimeType());
                return;
            }
        }
        response.sendError(NOT_FOUND);
    }
}