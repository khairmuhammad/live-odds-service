package org.football;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// This class contains unit tests for the Scoreboard class.
public class ScoreBoardTest {

    // Test the startMatch method.
    // Verifies that starting a new match adds it to the scoreboard with an initial score of 0-0.
    @Test
    public void testStartMatch() {
        // Create a new scoreboard instance
        ScoreBoard scoreboard = new ScoreBoard();

        // Start a match between Mexico and Canada
        scoreboard.startMatch("Mexico", "Canada");

        // Get the summary of ongoing matches
        List<Match> summary = scoreboard.getSummary();

        // Verify that there is one match in progress
        assertEquals(1, summary.size());

        // Verify that the match has the correct initial score
        assertEquals("Mexico 0 - Canada 0", summary.get(0).toString());
    }

    // Test the updateScore method.
    // Verifies that updating the score of an ongoing match correctly changes the match score.
    @Test
    public void testUpdateScore() {
        // Create a new scoreboard instance
        ScoreBoard scoreboard = new ScoreBoard();

        // Start a match between Mexico and Canada
        scoreboard.startMatch("Mexico", "Canada");

        // Update the score of the match
        scoreboard.updateScore("Mexico", 0, "Canada", 5);

        // Get the summary of ongoing matche
        List<Match> summary = scoreboard.getSummary();

        // Verify that the score has been updated correctly
        assertEquals("Mexico 0 - Canada 5", summary.get(0).toString());
    }

    // Test the finishMatch method.
    // Verifies that finishing a match removes it from the scoreboard.
    @Test
    public void testFinishMatch() {
        // Create a new scoreboard instance
        ScoreBoard scoreboard = new ScoreBoard();

        // Start a match between Mexico and Canada
        scoreboard.startMatch("Mexico", "Canada");

        // Finish the match
        scoreboard.finishMatch("Mexico");

        // Get the summary of ongoing match
        List<Match> summary = scoreboard.getSummary();

        // Verify that the match has been removed from the scoreboard
        assertTrue(summary.isEmpty());
    }

    // Test the getSummary method.
    // Verifies that the summary of matches is ordered by total score and start time correctly.
    @Test
    public void testGetSummary() {
        // Create a new scoreboard instance
        ScoreBoard scoreboard = new ScoreBoard();

        // Start multiple matches
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        // Update the scores of the matches
        scoreboard.updateScore("Mexico", 0, "Canada", 5);
        scoreboard.updateScore("Spain", 10, "Brazil", 2);
        scoreboard.updateScore("Germany", 2, "France", 2);
        scoreboard.updateScore("Uruguay", 6, "Italy", 6);
        scoreboard.updateScore("Argentina", 3, "Australia", 1);

        // Get the summary of ongoing matches
        List<Match> summary = scoreboard.getSummary();

        // Verify that the summary is ordered correctly
        assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString());
        assertEquals("Spain 10 - Brazil 2", summary.get(1).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(2).toString());
        assertEquals("Argentina 3 - Australia 1", summary.get(3).toString());
        assertEquals("Germany 2 - France 2", summary.get(4).toString());
    }
}
