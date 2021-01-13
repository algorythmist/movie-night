package com.tecacet.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resource does not exist in the system")
public class ResourceNotFoundException extends MovieServiceException {

    public ResourceNotFoundException(Throwable t) {
        super(t);
    }

    public ResourceNotFoundException() {
        super();
    }

}
