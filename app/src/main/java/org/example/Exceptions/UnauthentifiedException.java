package org.example.Exceptions;

public class UnauthentifiedException extends Exception {

    public UnauthentifiedException() {
        super("Utilisateur non authentifié");
    }

    public UnauthentifiedException(String message) {
        super(message);
    }

}
