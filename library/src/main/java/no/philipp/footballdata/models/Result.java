package no.philipp.footballdata.models;

import retrofit2.Response;

/**
 * Result of a {@link Fixture}
 */
public class Result {
    private Integer goalsHomeTeam;
    private Integer goalsAwayTeam;
    private Result halfTime;

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public Result setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
        return this;
    }

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public Result setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
        return this;
    }

    public Result getHalfTime() {
        return halfTime;
    }

    public Result setHalfTime(Result halfTime) {
        this.halfTime = halfTime;
        return this;
    }
}
