package de.exxcellent.challenge.controllers;

import de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector;
import de.exxcellent.challenge.inputReaders.CSVInputReader;
import de.exxcellent.challenge.inputReaders.IncorrectFileTypeException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This controller performs all steps necessary in order to fulfill the programming challenge.
 *
 * @author Philipp Jurinka
 */
public class ProgrammingChallengeController {

    /**
     * Solves the weather data challenge when called.
     * Internally uses a CSVInputReader to read the weather.csv file and
     * a MinDiscrepancySelector to choose the row with minimal difference between
     * MnT and MxT.
     * @return The entry in the "Day" column of the row, that has the minimal distance between "MnT" and "MxT"
     */
    public String solveWeatherTask() {
        return applyMinDiscrepancySelectorOnCSV(
                "src/main/resources/de/exxcellent/challenge/weather.csv",
                "MxT",
                "MnT",
                "Day");
    }

    /**
     * Applies a MinDiscrepancySelector on a given .csv file using a CSVInputReader and selects the cell specified by targetCell
     * Both external classes are created using the respective private methods of this class.
     * @param filePath the path, that belongs to the target .csv file
     * @param firstComparedColumnName the first of the two columns the MinDiscrepancySelector will compare
     * @param secondComparedColumnName the second of the two columns the MinDiscrepancySelector will compare
     * @param targetColumn the column specifying the cell to be received as a return value
     * @return the cell specified by targetColumn from the row with minimal difference between the given columns or an empty String if it could not be determined.
     */
    public String applyMinDiscrepancySelectorOnCSV(String filePath, String firstComparedColumnName, String secondComparedColumnName, String targetColumn){
        CSVInputReader reader = this.createCSVInputReader(filePath);
        if(null == reader)
            return "";

        ArrayList<String[]> readTable = reader.readData();
        if(null == readTable) {
            System.out.println("Failed to perform the task, because the file did not contain a table matching the expected format.");
            return "";
        }

        //Passing Max first, so that difference should be positive even without MAth.abs
        String[] selectedRow = this.applyMinDiscrepancySelector(readTable, firstComparedColumnName, secondComparedColumnName);
        if(null == selectedRow)
            return "";

        String selectedCell = this.selectEntry(readTable.get(0), selectedRow, targetColumn);
        if(null == selectedCell){
            return "";
        }
        return selectedCell;
    }

    /**
     * Creates a CSVInputReader on the file specified by the given path.
     * This method catches and logs all exceptions thrown by the reader.
     *
     * @param filePath the path, that the CSVInputReader receives
     * @return the created reader or null if it could not be created
     */
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

    /**
     * Uses the MinDiscrepancySelector class to select a row from the given table.
     * This method logs any erroneous results of the selector.
     *
     * @param table the table the MinDiscrepancySelector will be applied on
     * @param firstColumnName the first of the two columns the MinDiscrepancySelector will compare
     * @param secondColumnName the second of the two columns the MinDiscrepancySelector will compare
     * @return the row selected by the MinDiscrepancySelector or null if an error occurred
     */
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

    /**
     * Selects the cell from values, which is in the same column as the given columnToSelect.
     *
     * @param columns the names of the columns of which the columnToSelect should be one
     * @param values the values from which the targeted columnToSelect should be selected
     * @param columnToSelect the name of the desired column
     * @return the cell from values, which is on the same position as columnToSelect is on columns
     */
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

    /**
     * Solves the football challenge when called.
     * Internally uses a CSVInputReader to read the football.csv file and
     * a MinDiscrepancySelector to choose the row with minimal difference between
     * Goals and Goals Allowed.
     * @return The entry in the "Team" column of the row, that has the minimal distance between "Goals" and "Goals Allowed"
     */
    public String solveFootballTask(){
        return this.applyMinDiscrepancySelectorOnCSV(
                "src/main/resources/de/exxcellent/challenge/football.csv",
                "Goals",
                "Goals Allowed",
                "Team");
    }
}
