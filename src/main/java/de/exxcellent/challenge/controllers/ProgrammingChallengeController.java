package de.exxcellent.challenge.controllers;

import de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector;
import de.exxcellent.challenge.inputReaders.CSVInputReader;
import de.exxcellent.challenge.inputReaders.IncorrectFileTypeException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * This controller performs all steps necessary in order to fulfill the programming challenge.
 *
 * @author Philipp Jurinka
 */
public class ProgrammingChallengeController {

    /**
     * Solves the weatherdata challenge when called.
     * Internally uses a CSVInputReader to read the weather.csv file and
     * a MinDiscrepancySelector to choose the row with minimal difference between
     * MnT and MxT.
     * @return The first entry of the row, with minimal distance between MnT and MxT
     */
    public String solveWeatherTask() {
        CSVInputReader reader;
        try{
             reader = new CSVInputReader("src/main/resources/de/exxcellent/challenge/weather.csv");
        }catch(FileNotFoundException e){
            System.out.println("Failed to perform weather task, because the file specified does not exist.");
            return "";
        }catch(IncorrectFileTypeException e){
            System.out.println("Failed to perform the task, because the specified file is not a .csv file.");
            return "";
        }

        ArrayList<String[]> readTable = reader.readData();
        if(null == readTable) {
            System.out.println("Failed to perform the task, because the file did not contain a table matching the expected format.");
            return "";
        }


        //Passing Max first, so that difference should be positive even without MAth.abs
        MinDiscrepancySelector selector = new MinDiscrepancySelector("MxT","MnT");
        String[] selectedRow = selector.selectRow(readTable);
        if(null == selectedRow){
            System.out.println("Failed to perform the task, because there are either too many or too few column names in the table, that match the requested column names.");
            return "";
        }else if(Arrays.equals(new String[0], selectedRow)){
            System.out.println("Failed to perform the task, because the table contains too many non-integer values and a minimum could therefore not be found.");
            return "";
        }else{
            return selectedRow[0];
        }
    }
}
