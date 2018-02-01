package org.pes.onecemulator.model;

public class ApiError {

    private String error;

    ApiError() {}

    ApiError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}