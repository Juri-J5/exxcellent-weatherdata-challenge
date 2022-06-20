package de.exxcellent.challenge.inputReaders;

public class IncorrectFileTypeException extends Exception{
    public IncorrectFileTypeException(String errorMessage){
        super(errorMessage);
    }
}
