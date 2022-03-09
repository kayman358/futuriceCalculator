package com.futurice.futuricecalculus.model;

import lombok.Data;

@Data
public class Response {
    boolean error;
    String message;
    double number;
}
