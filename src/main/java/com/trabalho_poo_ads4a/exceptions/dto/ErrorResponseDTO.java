package com.trabalho_poo_ads4a.exceptions.dto;

import com.trabalho_poo_ads4a.exceptions.BaseExceptionError;
import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponseDTO implements Serializable {

    private int status;
    private String message;
    private String error;

    public ErrorResponseDTO(BaseExceptionError exception) {
        this.status = exception.getStatusCode().value();
        this.message = exception.getMessage();
        this.error = exception.getError();
    }

    public ErrorResponseDTO(int status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"status\":").append(status).append(",");
        sb.append("\"message\":").append("\"").append(message).append("\"").append(",");
        sb.append("\"error\":").append("\"").append(error).append("\"");
        sb.append("}");

        return sb.toString();
    }

}
