package de.exxcellent.challenge.inputReaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * Tests the {@link de.exxcellent.challenge.inputReaders.CSVInputReader} class.
 *
 * @author Philipp Jurinka
 */
public class CSVInputReaderTest {

    @Test
    void testReaderCreation(){
        try {                   //The filename does not exist and therefore the appropriate exception needs to be thrown
            new CSVInputReader("not a file path or name");
            Assertions.fail();
        }catch (FileNotFoundException e){
            //The file not being found and the appropriate error being thrown is correct
        }catch (WrongFileTypeException e){
            Assertions.fail();
        }
        try{                    //The fil is of a wrong type and therefore the appropriate exception needs to be thrown
            new CSVInputReader("src/main/resources/de/exxcellent/challenge/test.txt");
            Assertions.fail();
        }catch(FileNotFoundException e){
            Assertions.fail();
        }catch (WrongFileTypeException e){
            //The type of the file .txt and not .csv so this is the expected behaviour
        }
        try{                    //the call is correct and therefore no Exception should be thrown
            new CSVInputReader("src/main/resources/de/exxcellent/challenge/football.csv");
        }catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testReadData(){
        
    }
}
