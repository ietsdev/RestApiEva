package com.nttdata.evaluacion.util;

public class BusinessException extends Exception { 
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}