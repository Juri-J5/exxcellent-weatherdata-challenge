package de.exxcellent.challenge.actions.rowSelectors;

import java.util.ArrayList;

/**
 * The interface that all Actions, which perform the extraction of a single row from a table, must implement.
 *
 * @author Philipp Jurinka
 */
public interface RowSelector {

    /**
     * This method selects a specific row from the given table.
     * @param table The table, of which a single row has to be selected.
     * @return the row, which was selected.
     */
    public String[] selectRow(ArrayList<String[]> table);
}
