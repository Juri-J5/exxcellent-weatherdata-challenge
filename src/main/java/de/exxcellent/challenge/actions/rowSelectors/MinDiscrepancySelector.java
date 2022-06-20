package de.exxcellent.challenge.actions.rowSelectors;

import java.util.ArrayList;

public class MinDiscrepancySelector implements RowSelector{
    private String firstColumn;
    private String secondColumn;

    public MinDiscrepancySelector(String _firstColumn, String _secondColumn){
        this.firstColumn = _firstColumn;
        this.secondColumn = _secondColumn;
    }

    @Override
    public String[] selectRow(ArrayList<String[]> table) {
        //TODO
        return new String[0];
    }
}
