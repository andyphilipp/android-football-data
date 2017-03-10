package no.philipp.footballdata.models;

import java.util.Date;
import java.util.List;

/**
 * Created by andyphilipp on 22/11/2016.
 */

public class Head2Head {
    private int count;
    private Date timeFrameStart;
    private Date timeFrameEnd;
    private int homeTeamWins;
    private int awayTeamWins;
    private int draws;
    private Fixture lastHomeWinHomeTeam;
    private Fixture lastWinHomeTeam;
    private Fixture lastAwayWinAwayTeam;
    private Fixture lastWinAwayTeam;
    private List<Fixture> fixtures;

    public int getCount() {
        return count;
    }

    public Head2Head setCount(int count) {
        this.count = count;
        return this;
    }

    public Date getTimeFrameStart() {
        return timeFrameStart;
    }

    public Head2Head setTimeFrameStart(Date timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
        return this;
    }

    public Date getTimeFrameEnd() {
        return timeFrameEnd;
    }

    public Head2Head setTimeFrameEnd(Date timeFrameEnd) {
        this.timeFrameEnd = timeFrameEnd;
        return this;
    }

    public int getHomeTeamWins() {
        return homeTeamWins;
    }

    public Head2Head setHomeTeamWins(int homeTeamWins) {
        this.homeTeamWins = homeTeamWins;
        return this;
    }

    public int getAwayTeamWins() {
        return awayTeamWins;
    }

    public Head2Head setAwayTeamWins(int awayTeamWins) {
        this.awayTeamWins = awayTeamWins;
        return this;
    }

    public int getDraws() {
        return draws;
    }

    public Head2Head setDraws(int draws) {
        this.draws = draws;
        return this;
    }

    public Fixture getLastHomeWinHomeTeam() {
        return lastHomeWinHomeTeam;
    }

    public Head2Head setLastHomeWinHomeTeam(Fixture lastHomeWinHomeTeam) {
        this.lastHomeWinHomeTeam = lastHomeWinHomeTeam;
        return this;
    }

    public Fixture getLastWinHomeTeam() {
        return lastWinHomeTeam;
    }

    public Head2Head setLastWinHomeTeam(Fixture lastWinHomeTeam) {
        this.lastWinHomeTeam = lastWinHomeTeam;
        return this;
    }

    public Fixture getLastAwayWinAwayTeam() {
        return lastAwayWinAwayTeam;
    }

    public Head2Head setLastAwayWinAwayTeam(Fixture lastAwayWinAwayTeam) {
        this.lastAwayWinAwayTeam = lastAwayWinAwayTeam;
        return this;
    }

    public Fixture getLastWinAwayTeam() {
        return lastWinAwayTeam;
    }

    public Head2Head setLastWinAwayTeam(Fixture lastWinAwayTeam) {
        this.lastWinAwayTeam = lastWinAwayTeam;
        return this;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public Head2Head setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
        return this;
    }
}
