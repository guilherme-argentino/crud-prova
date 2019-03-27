package com.github.guilherme.crudprova.util;

/**
 * Classe utilitaria para abstracao de erros
 * @author Guilherme
 */
public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
