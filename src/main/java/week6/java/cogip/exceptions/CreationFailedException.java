package week6.java.cogip.exceptions;

public class CreationFailedException extends RuntimeException{
    public CreationFailedException(String errorMessage){
        super(errorMessage);
    }
}
