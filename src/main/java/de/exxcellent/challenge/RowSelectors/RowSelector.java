package de.exxcellent.challenge.RowSelectors;

import java.util.ArrayList;

public interface RowSelector {

    public String[] selectRow(ArrayList<String[]> table);
}
