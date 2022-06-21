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
        return applyMinDiscrepancySelectorOnCSV("src/main/resources/de/exxcellent/challenge/weather.csv",
                "MxT",
                "MnT",
                "Day");
    }

    public String applyMinDiscrepancySelectorOnCSV(String filePath, String firstColumnName, String secondColumnName, String targetColumn){
        CSVInputReader reader = this.createCSVInputReader(filePath);
        if(null == reader)
            return "";

        ArrayList<String[]> readTable = reader.readData();
        if(null == readTable) {
            System.out.println("Failed to perform the task, because the file did not contain a table matching the expected format.");
            return "";
        }

        //Passing Max first, so that difference should be positive even without MAth.abs
        String[] selectedRow = this.applyMinDiscrepancySelector(readTable, firstColumnName, secondColumnName);
        if(null == selectedRow)
            return "";

        String selectedCell = this.selectEntry(readTable.get(0), selectedRow, targetColumn);
        return selectedCell;
    }

    private CSVInputReader createCSVInputReader(String filePath){
        try{
            return new CSVInputReader(filePath);
        }catch(FileNotFoundException e){
            System.out.println("Failed to perform weather task, because the file specified does not exist.");
        }catch(IncorrectFileTypeException e){
            System.out.println("Failed to perform the task, because the specified file is not a .csv file.");
        }
        return null;
    }

    private String[] applyMinDiscrepancySelector(ArrayList<String[]> table, String firstColumnName, String secondColumnName){
        MinDiscrepancySelector selector = new MinDiscrepancySelector(firstColumnName,secondColumnName);
        String[] selectedRow = selector.selectRow(table);
        if(null == selectedRow){
            System.out.println("Failed to perform the task, because there are either too many or too few column names in the table, that match the requested column names.");
            return null;
        }else if(Arrays.equals(new String[0], selectedRow)){
            System.out.println("Failed to perform the task, because the table contains too many non-integer values and a minimum could therefore not be found.");
            return null;
        }else{
            return selectedRow;
        }
    }
    private String selectEntry(String[] columns, String[] values, String columnToSelect){
        if(columns.length != values.length) {
            System.out.println("Failed to perform the task, because the selected row does not match the number of columns in the table.");
            return null;
        }
        if(null == columnToSelect){
            System.out.println("Failed to perform the task, because no column was selected as output.");
            return null;
        }

        for (int i = 0; i < columns.length; i++) {
            if(columnToSelect.equals(columns[i])){
                return values[i];
            }
        }
        System.out.println("Failed to perform the task, because the specified table does not contain the output target column.");
        return null;
    }

    public String solveFootballTask(){
        //TODO
        return "";
    }
}
