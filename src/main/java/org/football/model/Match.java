package org.football.model;

public class Match {
    private String matchId;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;
    private long matchStartTime;

    /**
     * Constructor to initialize a match with the given home and away teams.
     * The initial score is set to 0-0 and the start time is recorded.
     *
     * @param matchId the id for the match
     * @param homeTeamName the name of the home team
     * @param awayTeamName the name of the away team
     */
    public Match(String matchId, String homeTeamName, String awayTeamName) {
        this.matchId = matchId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.matchStartTime = System.currentTimeMillis();
    }

    // Getters method
    public String getMatchId() {
        return matchId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public long getMatchStartTime() {
        return matchStartTime;
    }

    // Setter methods
    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    // Returns the total score of the match (home team score + away team score).
    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    @Override
    public String toString() {
        return homeTeamName + " " + homeTeamScore + " - " + awayTeamName + " " + awayTeamScore;
    }
}
