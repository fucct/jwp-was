package controller.page;

import controller.AbstractController;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.ResponseMetaData;
import webserver.response.ResponseMetaDataGenerator;

import java.io.IOException;

public class IndexPageController extends AbstractController {

    @Override
    public void service(final HttpRequest request, final HttpResponse response) throws IOException {
        ResponseMetaData responseMetaData = ResponseMetaDataGenerator.buildDefaultOkMetaData(request);
        response.setResponseMetaData(responseMetaData);

        doGet(request, response);
    }
}