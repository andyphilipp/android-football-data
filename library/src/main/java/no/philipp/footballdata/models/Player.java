package no.philipp.footballdata.models;

import java.util.Comparator;
import java.util.Date;

/**
 * A soccer player
 */
public class Player {
    private int id;
    private String name;
    private Position position;
    private int jerseyNumber;
    private Date dateOfBirth;
    private String nationality;
    private Date contractUntil;
    private String marketValue;

    public int getId() {
        return id;
    }

    public Player setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Player setPosition(Position position) {
        this.position = position;
        return this;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public Player setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Player setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Player setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Date getContractUntil() {
        return contractUntil;
    }

    public Player setContractUntil(Date contractUntil) {
        this.contractUntil = contractUntil;
        return this;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public Player setMarketValue(String marketValue) {
        this.marketValue = marketValue;
        return this;
    }

    public static Comparator<Player> NAME_COMPARATOR = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    public static Comparator<Player> JERSEY_NUMBER_COMPARATOR = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getJerseyNumber() - p2.getJerseyNumber();
        }
    };
}
