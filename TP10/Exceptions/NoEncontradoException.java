package TP10.Exceptions;

public class NoEncontradoException extends Exception {
    public NoEncontradoException(){
        super("NoEncontradoException");
    }

    public NoEncontradoException(String message){
        super(message);
    }
}
