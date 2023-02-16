package Exceptions;

public class UserNonUniqueException extends RuntimeException {
    public UserNonUniqueException(String s){
        super(s);
    }
}
