package org.football.service;

import org.football.exception.InvalidTeamNameException;
import org.football.exception.MatchAlreadyExistsException;
import org.football.exception.MatchNotFoundException;
import org.football.model.Match;

import java.util.*;

public class MatchService {
    private final Map<String, Match> matches = new HashMap<>();

    public String startMatch(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || awayTeamName == null || homeTeamName.isEmpty() || awayTeamName.isEmpty()) {
            throw new InvalidTeamNameException("Team names must not be null or empty");
        }
        if (homeTeamName.equals(awayTeamName)) {
            throw new InvalidTeamNameException("Home team and away team must be different");
        }
        for (Match match : matches.values()) {
            if (match.getHomeTeamName().equals(homeTeamName) || match.getAwayTeamName().equals(homeTeamName) ||
                    match.getHomeTeamName().equals(awayTeamName) || match.getAwayTeamName().equals(awayTeamName)) {
                throw new MatchAlreadyExistsException("A match involving one of these teams is already in progress");
            }
        }
        String matchId = UUID.randomUUID().toString();
        Match match = new Match(matchId, homeTeamName, awayTeamName);
        matches.put(matchId, match);
        return matchId;
    }

    public Match updateScore(String matchId, int homeTeamScore, int awayTeamScore) {
        Match match = matches.get(matchId);
        if (match == null) {
            throw new MatchNotFoundException("Match not found");
        }
        match.updateScore(homeTeamScore, awayTeamScore);
        return match;
    }

    public Match finishMatch(String matchId) {
        Match match = matches.remove(matchId);
        if (match == null) {
            throw new MatchNotFoundException("Match not found");
        }
        return match;
    }

    public Match getMatch(String matchId) {
        return matches.get(matchId);
    }

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
