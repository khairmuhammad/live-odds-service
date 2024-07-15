package org.football;

import junit.framework.TestCase;
import org.football.exception.InvalidTeamNameException;
import org.football.exception.MatchAlreadyExistsException;
import org.football.exception.MatchNotFoundException;
import org.football.service.MatchService;
import org.junit.jupiter.api.Test;
import org.football.model.Match;

import static org.junit.Assert.*;

public class MatchServiceTest extends TestCase {
    @Test
    public void testStartMatch() {
        MatchService service = new MatchService();
        String matchId = service.startMatch("TeamA", "TeamB");
        assertNotNull(matchId);
    }

    @Test
    public void testUpdateScore() {
        MatchService service = new MatchService();
        String matchId = service.startMatch("TeamA", "TeamB");
        Match updatedMatch = service.updateScore(matchId, 1, 1);
        assertEquals(1, updatedMatch.getHomeTeamScore());
        assertEquals(1, updatedMatch.getAwayTeamScore());
    }

    @Test
    public void testFinishMatch() {
        MatchService service = new MatchService();
        String matchId = service.startMatch("TeamA", "TeamB");
        Match finishedMatch = service.finishMatch(matchId);
        assertEquals("TeamA", finishedMatch.getHomeTeamName());
        assertEquals("TeamB", finishedMatch.getAwayTeamName());
    }

    @Test
    public void testEdgeCases() {
        MatchService service = new MatchService();
        assertThrows(InvalidTeamNameException.class, () -> {
            service.startMatch(null, "TeamB");
        });
        assertThrows(InvalidTeamNameException.class, () -> {
            service.startMatch("TeamA", "");
        });
        assertThrows(InvalidTeamNameException.class, () -> {
            service.startMatch("TeamA", "TeamA");
        });
        String matchId = service.startMatch("TeamA", "TeamB");
        assertThrows(MatchAlreadyExistsException.class, () -> {
            service.startMatch("TeamA", "TeamC");
        });
        assertThrows(MatchAlreadyExistsException.class, () -> {
            service.startMatch("TeamC", "TeamA");
        });
        assertThrows(MatchNotFoundException.class, () -> {
            service.updateScore("InvalidMatchId", 1, 1);
        });
        assertThrows(MatchNotFoundException.class, () -> {
            service.finishMatch("InvalidMatchId");
        });
    }
}
