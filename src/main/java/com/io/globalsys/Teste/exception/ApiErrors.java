package com.io.globalsys.Teste.exception;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {


    private List<String> erros;

    public ApiErrors(List<String> errors){
        this.erros = errors;
    }

    public ApiErrors(String message){
        this.erros = Arrays.asList(message);
    }


    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
