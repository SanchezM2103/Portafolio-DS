package com.portafolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProfileAlreadyExistsException extends RuntimeException {

    public ProfileAlreadyExistsException() {
        super("Ya existe un perfil");
    }
}
