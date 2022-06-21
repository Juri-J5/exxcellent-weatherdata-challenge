package de.exxcellent.challenge.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the {@link de.exxcellent.challenge.controllers.ProgrammingChallengeController} class.
 *
 * @author Philipp Jurinka
 */
public class ProgrammingChallengeControllerTest {
    private ProgrammingChallengeController controller;

    @BeforeEach
    void SetUp(){
        this.controller = new ProgrammingChallengeController();
    }

    @Test
    void testSolveWeatherTask(){
        Assertions.assertEquals("14", this.controller.solveWeatherTask());
    }

    @Test
    void testSolveFootballTask(){
        Assertions.assertEquals("Aston_Villa", this.controller.solveFootballTask());
    }
}
