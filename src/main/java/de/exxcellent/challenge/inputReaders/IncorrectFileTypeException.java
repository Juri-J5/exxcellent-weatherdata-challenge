package de.exxcellent.challenge.inputReaders;

/**
 * An Exception that can be thrown, when a {@link de.exxcellent.challenge.inputReaders.InputReader} for a specific fileType
 * is called with a file of an incorrect type.
 */
public class IncorrectFileTypeException extends Exception{
    public IncorrectFileTypeException(String errorMessage){
        super(errorMessage);
    }
}
