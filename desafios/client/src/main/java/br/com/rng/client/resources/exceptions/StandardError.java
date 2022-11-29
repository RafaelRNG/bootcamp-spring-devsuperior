package br.com.rng.client.resources.exceptions;

public class StandardError {

    private String title;
    private String message;
    private Integer code;

    public StandardError(String title, String message, Integer code) {
        this.title = title;
        this.message = message;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
