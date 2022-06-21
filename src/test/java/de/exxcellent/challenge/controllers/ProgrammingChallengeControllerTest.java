package de.exxcellent.challenge.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Tests the {@link de.exxcellent.challenge.controllers.ProgrammingChallengeController} class.
 *
 * @author Philipp Jurinka
 */
public class ProgrammingChallengeControllerTest {

    @Test
    void testSolveWeatherTask(){
        ProgrammingChallengeController controller = new ProgrammingChallengeController();
        Assertions.assertEquals("14", controller.solveWeatherTask());
    }
}
