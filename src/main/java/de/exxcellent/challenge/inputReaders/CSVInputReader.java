package de.exxcellent.challenge.inputReaders;

import java.io.*;
import java.util.ArrayList;

/**
 * This class takes the fileName of a .csv file on initialization
 * and can be used to read the content of the content of the file
 * as an {@link java.util.ArrayList} of {@link java.lang.String}.
 *
 * @author Philipp Jurinka
 */
public class CSVInputReader implements InputReader{

    private BufferedReader reader;

    /**
     * Creates a CSVInputReader that uses a BufferedReader to read .csv files.
     * @param filePath the path from the repository root
     * @throws FileNotFoundException if the filePath does not belong to a readable file
     * @throws WrongFileTypeException if the type of the specified file is any other than .csv or missing entirely
     */
    public CSVInputReader(String filePath) throws FileNotFoundException, WrongFileTypeException{
        this.reader = new BufferedReader(new FileReader(filePath));
        if(!filePath.endsWith(".csv"))
            throw new WrongFileTypeException("The CSVInputReader can only handle .csv files");
    }

    /**
     * Reads the data of the file specified by fileName.
     * @return the content of the file or null if the content of file cannot be matched with the return type.
     */
    @Override
    public ArrayList<String[]> readData() {
        try{
            ArrayList<String[]> result = new ArrayList<>();
            String line;
            while ((line = this.reader.readLine()) != null)
            {
                result.add(line.split(","));
            }
            return result;
        }catch (IOException e) {
            return null;
        }
    }
}
