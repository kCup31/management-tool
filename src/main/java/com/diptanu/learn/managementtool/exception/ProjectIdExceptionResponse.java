package com.diptanu.learn.managementtool.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProjectIdExceptionResponse {

    private String projectIdentifier;

    public ProjectIdExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
