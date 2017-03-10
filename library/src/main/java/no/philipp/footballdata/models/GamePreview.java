package no.philipp.footballdata.models;

/**
 * Created by andyphilipp on 22/11/2016.
 */

public class GamePreview {
    private Fixture fixture;
    private Head2Head head2Head;

    public Fixture getFixture() {
        return fixture;
    }

    public GamePreview setFixture(Fixture fixture) {
        this.fixture = fixture;
        return this;
    }

    public Head2Head getHead2Head() {
        return head2Head;
    }

    public GamePreview setHead2Head(Head2Head head2Head) {
        this.head2Head = head2Head;
        return this;
    }
}
