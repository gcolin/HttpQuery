package net.gcolin.httpquery;

import java.io.InputStream;

public class HttpHandlerImpl implements HttpHandler {
    public static final String ERROR_MESSAGE = "cannot send request";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private static final String POST_METHOD = "POST";
    private static final String GET_METHOD = "GET";
    private static final String HEAD_METHOD = "HEAD";
    private static final String OPTIONS_METHOD = "OPTIONS";
    private static final String TRACE_METHOD = "TRACE";

    @Override
    public Request get(String uri) {
        return new RequestImpl(GET_METHOD, uri);
    }

    @Override
    public Request delete(String uri) {
        return new RequestImpl(DELETE_METHOD, uri);
    }

    @Override
    public RequestWithPayload put(String uri, Object obj) {
        return new RequestWithPayloadImpl(PUT_METHOD, uri, obj);
    }

    @Override
    public Request put(String uri, byte[] data) {
        return new RequestWithPayloadImpl(PUT_METHOD, uri, data).serialize();
    }

    @Override
    public Request put(String uri, String str) {
        return new RequestWithPayloadImpl(PUT_METHOD, uri, str).serialize();
    }

    @Override
    public Request put(String uri, InputStream inStream) {
        return new RequestWithPayloadImpl(PUT_METHOD, uri, inStream)
                .serialize();
    }

    @Override
    public RequestWithPayload post(String uri, Object obj) {
        return new RequestWithPayloadImpl(POST_METHOD, uri, obj);
    }

    @Override
    public Request post(String uri, byte[] data) {
        return new RequestWithPayloadImpl(POST_METHOD, uri, data).serialize();
    }

    @Override
    public Request post(String uri, InputStream inStream) {
        return new RequestWithPayloadImpl(POST_METHOD, uri, inStream)
                .serialize();
    }

    @Override
    public Request post(String uri, String str) {
        return new RequestWithPayloadImpl(POST_METHOD, uri, str).serialize();
    }

    @Override
    public Request trace(String uri) {
        return new RequestImpl(TRACE_METHOD, uri);
    }

    @Override
    public Request head(String uri) {
        return new RequestImpl(HEAD_METHOD, uri);
    }

    @Override
    public Request options(String uri) {
        return new RequestImpl(OPTIONS_METHOD, uri);
    }

}
