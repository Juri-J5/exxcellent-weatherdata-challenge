package de.exxcellent.challenge.actions.rowSelecors;

import de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests the {@link de.exxcellent.challenge.actions.rowSelectors.MinDiscrepancySelector} class.
 *
 * @author Philipp Jurinka
 */
public class MinDiscrepancySelectorTest {

    private MinDiscrepancySelector selector;

    @BeforeEach
    void setUp(){
        selector = new MinDiscrepancySelector("value1","value2");
    }

    @Test
    void testSelectRowNullInput() {
        //tests if the method can handle null input
        Assertions.assertNull(selector.selectRow(null));
    }

    @Test
    void testSelectRowMissingColumn() {
        //tests if the return value is null for missing column(s) in the table
        ArrayList<String[]> table = new ArrayList<>();
        table.add(new String[]{"foo", "value1"});
        table.add(new String[]{"0","0"});
        Assertions.assertNull(selector.selectRow(table));
        table.remove(0);
        table.add(0, new String[]{"foo", "bar"});
        Assertions.assertNull(selector.selectRow(table));
    }

    @Test
    void testSelectRowTooFewColumns(){
        ArrayList<String[]> table = new ArrayList<>();
        table.add(new String[]{"value2"});
        table.add(new String[]{"0"});
        Assertions.assertNull(selector.selectRow(table));
    }

    @Test
    void testSelectRowDuplicateColumn() {
        ArrayList<String[]> table = new ArrayList<>();
        table.add(new String[]{"foo","bar","value2","value1","value2"});
        table.add(new String[]{"0","0","0","0","0"});
        Assertions.assertNull(selector.selectRow(table));

        table.clear();
        table.add(new String[]{"value1", "value1"});
        table.add(new String[]{"0","0"});
        Assertions.assertNull(selector.selectRow(table));
    }

    @Test
    void testSelectRowCorrectMinimum() {
        ArrayList<String[]> table = new ArrayList<>();
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
