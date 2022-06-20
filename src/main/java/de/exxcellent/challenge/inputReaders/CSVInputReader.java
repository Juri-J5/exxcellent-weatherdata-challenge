package de.exxcellent.challenge.inputReaders;

import java.util.ArrayList;

/**
 * This class takes the fileName of a .csv file on initialization
 * and can be used to read the content of the content of the file
 * as an {@link java.util.ArrayList} of {@link java.lang.String}.
 *
 * @author Philipp Jurinka
 */
public class CSVInputReader implements InputReader{

    private String fileName;

    public CSVInputReader(String _fileName) throws WrongFileTypeException{
        this.fileName = _fileName;
    }

    /**
     * Reads the data of the file specified by fileName.
     * @return the content of the file or null if the content of file cannot be matched with the return type.
     */
    @Override
    public ArrayList<String[]> readData() {
        //TODO
        return null;
    }
}