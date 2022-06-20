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
     * If the targeted columns do not contain integer values, the respective row is omitted in the calculation
     * @param table A representation of a table, where each String array represents one row are rows.
     *              The first row must contain the names of the columns.
     * @return the selected row. Or null if any of the compared columns does not exist within the given table or there are multiple matching columns. Or an Array of length 0, if no minimum was found (only possible with non integer entries)
     */
    @Override
    public String[] selectRow(ArrayList<String[]> table) {
        if(null == table ||
                table.size() <= 1 ||                            //the table does not exist or has no entries
                table.get(0).length <2)                         //the table has less than two columns
            return null;

        //Find out the indices of both target columns and check for multiple targets
        int firstIndex = -1;
        int secondIndex = -1;
        for(int i = 0; i < table.get(0).length; i++){
            if(this.firstColumn.equals(table.get(0)[i])) {
                if (firstIndex != -1)
                    return null;
                firstIndex = i;
            }
            if(this.secondColumn.equals(table.get(0)[i])){
                if(secondIndex != -1)
                    return null;
                secondIndex = i;
            }
        }
        //check if both columns are present
        if(firstIndex == -1 || secondIndex == -1){
            return null;
        }

        int resultIndex = -1;
        int minDifference = Integer.MAX_VALUE;

        for (int i = 1; i < table.size(); i++) {
            try {
                int difference = Math.abs(
                        Integer.parseInt(table.get(i)[firstIndex])
                                - Integer.parseInt(table.get(i)[secondIndex]));
                if (difference < minDifference) {
                    minDifference = difference;
                    resultIndex = i;
                }
            }catch(NumberFormatException e){
                continue;
            }
        }
        if(resultIndex < 0)
            return new String[0];
        else
            return table.get(resultIndex);
    }
}
