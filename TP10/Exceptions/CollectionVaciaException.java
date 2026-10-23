package TP10.Exceptions;

public class CollectionVaciaException extends Exception {
    public CollectionVaciaException(){
        super("CollectionVaciaException");
    }

    public CollectionVaciaException(String message){
        super(message);
    }
}
 