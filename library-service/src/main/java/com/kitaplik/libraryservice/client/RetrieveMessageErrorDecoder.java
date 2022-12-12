package com.kitaplik.libraryservice.client;

import com.kitaplik.libraryservice.exception.BookNotFoundException;
import com.kitaplik.libraryservice.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

        private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methedKey, Response response) {
        ExceptionMessage message = null;
        try(InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());

        } catch (Exception e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()){
            case 404:
                return new BookNotFoundException(message);
            default:
                return errorDecoder.decode(methedKey,response);
        }
    }
}

