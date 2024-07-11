package org.football;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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
        ScoreBoard scoreboard = new ScoreBoard();

        scoreboard.startMatch("Mexico", "Canada");

        scoreboard.updateScore("Mexico", 0, "Canada", 5);

        List<Match> summary = scoreboard.getSummary();

        assertEquals("Mexico 0 - Canada 5", summary.get(0).toString());
    }

    // Test the finishMatch method.
    // Verifies that finishing a match removes it from the scoreboard.
    @Test
    public void testFinishMatch() {
        ScoreBoard scoreboard = new ScoreBoard();

        scoreboard.startMatch("Mexico", "Canada");

        scoreboard.finishMatch("Mexico");

        List<Match> summary = scoreboard.getSummary();

        // Verify that the match has been removed from the scoreboard
        assertTrue(summary.isEmpty());
    }

    // Test the getSummary method.
    // Verifies that the summary of matches is ordered by total score and start time correctly.
    @Test
    public void testGetSummary() {
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

    // Test starting a match with duplicate teams.
    // Verifies that an exception is thrown if a team is already in another match.
    @Test
    public void testStartMatchWithDuplicateTeams() {
        ScoreBoard scoreboard = new ScoreBoard();
        scoreboard.startMatch("Mexico", "Canada");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch("Mexico", "Brazil");
        });

        assertEquals("One team cannot have two matches at the same time.", exception.getMessage());
    }

    // Test starting a match with the same home and away teams.
    // This test assumes that having the same team as both home and away is invalid.
    @Test
    public void testStartMatchWithSameTeams() {
        ScoreBoard scoreboard = new ScoreBoard();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch("Mexico", "Mexico");
        });

        assertEquals("One team cannot have two matches at the same time.", exception.getMessage());
    }

    // Test updating the score for a non-existent match.
    // Verifies that an exception is thrown if the match does not exist.
    @Test
    public void testUpdateScoreForNonExistentMatch() {
        ScoreBoard scoreboard = new ScoreBoard();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Mexico", 0, "Canada", 5);
        });

        assertEquals("Match not found.", exception.getMessage());
    }

    // Test updating the score with incorrect team names.
    // Verifies that an exception is thrown if the team names do not match any ongoing match.
    @Test
    public void testUpdateScoreWithIncorrectTeams() {
        ScoreBoard scoreboard = new ScoreBoard();
        scoreboard.startMatch("Mexico", "Canada");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Spain", 10, "Brazil", 2);
        });

        assertEquals("Match not found.", exception.getMessage());
    }

    // Test updating the score with negative values.
    // Verifies that an exception is thrown if negative scores are provided.
    @Test
    public void testUpdateScoreWithNegativeValues() {
        ScoreBoard scoreboard = new ScoreBoard();
        scoreboard.startMatch("Mexico", "Canada");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Mexico", -1, "Canada", 5);
        });

        assertEquals("Scores cannot be negative.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Mexico", 0, "Canada", -5);
        });

        assertEquals("Scores cannot be negative.", exception.getMessage());
    }

    // Test finishing a non-existent match.
    // Verifies that the operation does nothing or raises an exception if the match does not exist.
    @Test
    public void testFinishNonExistentMatch() {
        ScoreBoard scoreboard = new ScoreBoard();

        // Attempting to finish a match that does not exist should not affect the scoreboard
        scoreboard.finishMatch("Mexico");

        // Verify that the scoreboard is still empty
        List<Match> summary = scoreboard.getSummary();
        assertTrue(summary.isEmpty());
    }
}
