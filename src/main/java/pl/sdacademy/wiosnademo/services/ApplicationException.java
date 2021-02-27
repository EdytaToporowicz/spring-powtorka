package pl.sdacademy.wiosnademo.services;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}
