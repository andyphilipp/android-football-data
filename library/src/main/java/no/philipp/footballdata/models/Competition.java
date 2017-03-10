package no.philipp.footballdata.models;

import java.util.Date;

/**
 * A particular season of a particular league, i.e. Premier League 2016/2017
 */
public class Competition {
    private int id;
    private String caption;
    private String league;
    private int year;
    private int currentMatchday;
    private int numberOfMatchdays;
    private int numberOfTeams;
    private int numberOfGames;
    private Date lastUpdated;

    public int getId() {
        return id;
    }

    public Competition setId(int id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Competition setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getLeague() {
        return league;
    }

    public Competition setLeague(String league) {
        this.league = league;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Competition setYear(int year) {
        this.year = year;
        return this;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public Competition setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
        return this;
    }

    public int getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public Competition setNumberOfMatchdays(int numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
        return this;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public Competition setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        return this;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public Competition setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
        return this;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Competition setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    @Override
    public String toString() {
        return getCaption();
    }
}