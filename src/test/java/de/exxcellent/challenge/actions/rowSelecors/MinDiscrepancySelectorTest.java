package de.exxcellent.challenge.actions.rowSelecors;

import de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests the {@link de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector} class.
 *
 * @author Philipp Jurinka
 */
public class MinDiscrepancySelectorTest {

    @Test
    void testSelectRow(){
        MinDiscrepancySelector selector = new MinDiscrepancySelector("value1","value2");

        //tests if the method can handle null input
        Assertions.assertNull(selector.selectRow(null));
        
        //tests if the return value is null for missing column(s) in the table
        ArrayList<String[]> table = new ArrayList<>();
        table.add(new String[]{"foo", "value1"});
        Assertions.assertNull(selector.selectRow(table));
        table.remove(0);
        table.add(new String[]{"value2"});
        Assertions.assertNull(selector.selectRow(table));
        table.remove(0);
        table.add(new String[]{"foo", "bar"});
        Assertions.assertNull(selector.selectRow(table));

        //tests whether the method is capable of finding a correct minimum
        table.add(new String[]{"value1","Id","value2"});
        table.add(new String[]{"1","0","20"});
        table.add(new String[]{"4","1","10"});
        String[] actual = selector.selectRow(table);
        for (int i = 0; i < table.get(0).length; i++) {
            Assertions.assertEquals(table.get(2)[i],actual[i]);
        }

        //tests if the method returns the first entry, if there are multiple minima
        table.add(new String[]{"0","2","6"});
        actual = selector.selectRow(table);
        Assertions.assertEquals(table.get(3)[1],actual[1]);
    }
}
