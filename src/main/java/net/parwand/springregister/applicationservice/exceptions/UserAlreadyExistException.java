package net.parwand.springregister.applicationservice.exceptions;


public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String mg) {
        super(mg);
    }
}
