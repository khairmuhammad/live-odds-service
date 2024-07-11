package org.football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages the football scoreboard, allowing operations
 * such as starting a match, updating scores, finishing a match,
 * and getting a summary of ongoing matches.
 */
public class ScoreBoard {
    private Map<String, Match> matches;

    // Constructor to initialize the scoreboard with an empty match list.
    public ScoreBoard() {
        matches = new HashMap<>();
    }

    /**
     * Starts a new match with the given home and away teams and adds it to the scoreboard.
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @throws IllegalArgumentException if one of the teams is already in another match
     */
    public void startMatch(String homeTeam, String awayTeam) {
        if (homeTeam.equals(awayTeam) || matches.containsKey(homeTeam) || matches.containsKey(awayTeam)) {
            throw new IllegalArgumentException("One team cannot have two matches at the same time.");
        }
        matches.put(homeTeam, new Match(homeTeam, awayTeam));
    }

    /**
     * Updates the score of an ongoing match.
     *
     * @param homeTeam the name of the home team
     * @param homeTeamScore the new score of the home team
     * @param awayTeam the name of the away team
     * @param awayTeamScore the new score of the away team
     * @throws IllegalArgumentException if the match is not found or if scores are negative
     */
    public void updateScore(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) {
        Match match = matches.get(homeTeam);
        if (match == null || !match.getAwayTeam().equals(awayTeam)) {
            throw new IllegalArgumentException("Match not found.");
        }
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
    }

    /**
     * Finishes a match, removing it from the scoreboard.
     *
     * @param homeTeam the name of the home team
     */
    public void finishMatch(String homeTeam) {
        matches.remove(homeTeam);
    }

    /**
     * Gets a summary of ongoing matches, ordered by total score.
     * Matches with the same total score are ordered by the most recently started match.
     *
     * @return a list of ongoing matches sorted by total score and start time
     */
    public List<Match> getSummary() {
        List<Match> matchList = new ArrayList<>(matches.values());
        matchList.sort((match1, match2) -> {
            int scoreComparison = Integer.compare(match2.getTotalScore(), match1.getTotalScore());
            if (scoreComparison == 0) {
                return Long.compare(match2.getMatchStartTime(), match1.getMatchStartTime());
            }
            return scoreComparison;
        });
        return matchList;
    }

}
