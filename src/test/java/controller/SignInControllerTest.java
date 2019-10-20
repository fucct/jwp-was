package controller;

import db.DataBase;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.FileIoUtils;
import webserver.common.HttpStatus;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static support.Constants.*;
import static webserver.support.ConStants.*;

public class SignInControllerTest {
    private static final String GET_REQUEST_MESSAGE =
            "GET /index.html HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: keep-alive\n" +
                    "Accept: text/html";

    private static final String POST_REQUEST_MESSAGE =
            "POST /user/create HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: keep-alive\n" +
                    "Content-Length: 33\n" +
                    "Content-Type: application/x-www-form-urlencoded\n" +
                    "Accept: */*\n" +
                    "\n" +
                    "userId=javajigi&password=password";

    @DisplayName("/user/login GET 요청")
    @Test
    void doGet() throws IOException, URISyntaxException {
        HttpRequest httpRequest = HttpRequest.of(new ByteArrayInputStream(GET_REQUEST_MESSAGE.getBytes()));
        HttpResponse httpResponse = new HttpResponse();
        Controller controller = new SignInController();
        controller.service(httpRequest, httpResponse);

        HttpResponse httpResponseToCompare = new HttpResponse();
        httpResponseToCompare.setStatusLine(httpRequest, HttpStatus.OK);
        httpResponseToCompare.setHeader(HEADER_FIELD_CONTENT_TYPE, "text/html;charset=utf-8");
        httpResponseToCompare.setHeader(HEADER_FIELD_CONTENT_LENGTH, String.valueOf(httpResponse.getResponseBody().getLengthOfContent()));
        httpResponseToCompare.setBody(FileIoUtils.loadFileFromClasspath(TEMPLATE_FILE_PATH + "/user/login.html"));

        assertThat(httpResponse).isEqualTo(httpResponseToCompare);
    }

    @DisplayName("/user/login POST 요청")
    @Test
    void doPost() throws FileNotFoundException {
        DataBase.addUser(new User(TEST_USER_ID, TEST_USER_PASSWORD, TEST_USER_NAME, TEST_USER_EMAIL));
        HttpRequest httpRequest = HttpRequest.of(new ByteArrayInputStream(POST_REQUEST_MESSAGE.getBytes()));
        HttpResponse httpResponse = new HttpResponse();
        Controller controller = new SignInController();
        controller.service(httpRequest, httpResponse);

        HttpResponse httpResponseToCompare = new HttpResponse();
        httpResponseToCompare.setStatusLine(httpRequest, HttpStatus.FOUND);
        httpResponseToCompare.setHeader(HEADER_FIELD_LOCATION, "http://localhost:8080/index.html");

        assertThat(httpResponse).isEqualTo(httpResponseToCompare);
    }
}