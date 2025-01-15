package com.api.k.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class RestResponseStructure {
    private HttpStatus status;
    private String message;
}
