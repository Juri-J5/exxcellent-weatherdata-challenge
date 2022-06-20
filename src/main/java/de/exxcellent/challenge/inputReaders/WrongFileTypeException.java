package de.exxcellent.challenge.inputReaders;

public class WrongFileTypeException extends Exception{
    public WrongFileTypeException(String errorMessage){
        super(errorMessage);
    }
}
