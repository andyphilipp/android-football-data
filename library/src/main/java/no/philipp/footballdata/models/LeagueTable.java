package no.philipp.footballdata.models;

import java.util.List;

/**
 * A League table, showing the standings of the {@link Competition}
 */
public class LeagueTable {
    private String leagueCaption;
    private int matchday;
    private List<Standing> standing;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public LeagueTable setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
        return this;
    }

    public int getMatchday() {
        return matchday;
    }

    public LeagueTable setMatchday(int matchday) {
        this.matchday = matchday;
        return this;
    }

    public List<Standing> getStanding() {
        return standing;
    }

    public LeagueTable setStanding(List<Standing> standing) {
        this.standing = standing;
        return this;
    }
}
