package controller;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.ResponseStatus;
import utils.FileIoUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserFormController extends AbstractController {
    public static UserFormController getInstance() {
        return UserFormControllerLazyHolder.INSTANCE;
    }

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            httpResponse.setBody(FileIoUtils.loadFileFromClasspath("./templates" + "/user/form" + ".html"));
            httpResponse.setResponseStatus(ResponseStatus.OK);
            httpResponse.addHeaderAttribute("Content-Type", "text/html;charset=utf-8");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static class UserFormControllerLazyHolder {
        private static final UserFormController INSTANCE = new UserFormController();
    }
}