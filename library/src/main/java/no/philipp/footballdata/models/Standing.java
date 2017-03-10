package no.philipp.footballdata.models;

import java.net.URL;

/**
 * Standing for a particular {@link Team}, corresponds to a row in the {@link LeagueTable}.
 */
public class Standing {
    private int position;
    private String teamName;
    private URL crestURI;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int wins;
    private int draws;
    private int losses;

    public int getPosition() {
        return position;
    }

    public Standing setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public Standing setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public URL getCrestURI() {
        return crestURI;
    }

    public Standing setCrestURI(URL crestURI) {
        this.crestURI = crestURI;
        return this;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public Standing setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public Standing setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getGoals() {
        return goals;
    }

    public Standing setGoals(int goals) {
        this.goals = goals;
        return this;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public Standing setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
        return this;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public Standing setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public Standing setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getDraws() {
        return draws;
    }

    public Standing setDraws(int draws) {
        this.draws = draws;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public Standing setLosses(int losses) {
        this.losses = losses;
        return this;
    }
}