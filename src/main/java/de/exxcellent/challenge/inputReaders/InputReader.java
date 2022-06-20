package de.exxcellent.challenge.inputReaders;

import java.util.ArrayList;

/**
 * The interface that all Readers must implement, which extract a table represented by an {@link java.util.ArrayList} of
 * {@link java.lang.String} arrays from a certain filetype.
 *
 * @author Philipp Jurinka
 */
public interface InputReader {

    /**
     * This method extracts a table from a previously specified file.
     * @return the extracted table.
     */
    public ArrayList<String[]> readData();
}
