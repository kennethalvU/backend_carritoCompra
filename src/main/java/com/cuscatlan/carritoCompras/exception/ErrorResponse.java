package com.cuscatlan.carritoCompras.exception;

public class ErrorResponse {

    private String message;  // Mensaje de error
    private int errorCode;   // Código de error

    // Constructor para inicializar los campos
    public ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    // Getters y setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
