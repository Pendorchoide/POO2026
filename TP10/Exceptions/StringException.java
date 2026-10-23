package TP10.Exceptions;

public class StringException extends Exception {
    
    public StringException(){
        super("String Exception");
    }

    public StringException(String message){
        super(message);
    }
}
