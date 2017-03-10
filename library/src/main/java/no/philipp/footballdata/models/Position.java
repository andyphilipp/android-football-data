package no.philipp.footballdata.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andyphilipp on 22/11/2016.
 */

public enum Position {
    @SerializedName("Keeper")
    KEEPER("Keeper"),
    @SerializedName("Left-Back")
    LEFT_BACK("Left-Back"),
    @SerializedName("Centre Back")
    CENTRE_BACK("Centre Back"),
    @SerializedName("Right-Back")
    RIGHT_BACK("Right-Back"),
    @SerializedName("Defensive Midfield")
    DEFENSIVE_MIDFIELD("Defensive Midfield"),
    @SerializedName("Central Midfield")
    CENTRAL_MIDFIELD("Central Midfield"),
    @SerializedName("Attacking Midfield")
    ATTACKING_MIDFIELD("Attacking Midfield"),
    @SerializedName("Left Wing")
    LEFT_WING("Left Wing"),
    @SerializedName("Right Wing")
    RIGHT_WING("Right Wing"),
    @SerializedName("Secondary Striker")
    SECONDARY_STRIKER("Secondary Striker"),
    @SerializedName("Centre Forward")
    CENTRE_FORWARD("Centre Forward");

    String value;

    Position(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
