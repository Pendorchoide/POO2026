package TP10.Exceptions;

public class EnteroPositivoException extends Exception {
    public EnteroPositivoException(){
        super("EnteroPositivoException");
    }

    public EnteroPositivoException(String message){
        super(message);
    }
}
