package lt.ca.javau11.gr.carservice.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){

        super(message);
    }

}
