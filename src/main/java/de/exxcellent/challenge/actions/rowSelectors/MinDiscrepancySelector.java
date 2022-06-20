package de.exxcellent.challenge.actions.rowSelectors;

import java.util.ArrayList;

/**
 * An action that takes two column names upon creation.
 * Afterwards the selectRow method can be called to find the entry with the minimal discrepancy between the given columns.
 *
 * @author Philipp Jurinka
 */
public class MinDiscrepancySelector implements RowSelector{
    private String firstColumn;
    private String secondColumn;

    public MinDiscrepancySelector(String _firstColumn, String _secondColumn){
        this.firstColumn = _firstColumn;
        this.secondColumn = _secondColumn;
    }

    /**
     * Selects the row from table, where the difference between the columns specified by firstColumn and secondColumn.
     * If multiple rows have the same difference, the first occurrence is chosen.
     * @param table A representation of a table, where each String array represents one row are rows. The first row must contain the names of the columns.
     * @return The selected row or null if any of the compared columns does not exist within the given table
     */
    @Override
    public String[] selectRow(ArrayList<String[]> table) {
        //TODO
        return new String[0];
    }
}
