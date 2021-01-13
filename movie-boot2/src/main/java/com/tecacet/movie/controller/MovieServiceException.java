package com.tecacet.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "There was a problem processing your request")
public class MovieServiceException extends Exception {

    public MovieServiceException(Throwable t) {
        super(t);
    }

    public MovieServiceException() {
        super();
    }

}
