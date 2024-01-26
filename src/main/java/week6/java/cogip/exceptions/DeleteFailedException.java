package week6.java.cogip.exceptions;

public class DeleteFailedException extends RuntimeException {
    public DeleteFailedException (String errorMessage){
        super(errorMessage);
    }
}
