package no.philipp.footballdata;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Competition;
import no.philipp.footballdata.models.Fixture;
import no.philipp.footballdata.models.GamePreview;
import no.philipp.footballdata.models.LeagueTable;
import no.philipp.footballdata.models.Player;
import no.philipp.footballdata.models.Team;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Testing the {@link FootballData} API
 */
public class FootballDataTest {
    private static final int PL_2016 = 426;
    private static final int ARSENAL_FC = 57;
    private static final int BRAZIL_VS_GERMANY = 149461;
    private FootballData footballData;

    @Before
    public void setUp() throws Exception {
        footballData = FootballData.getInstance(new MockInterceptor());
    }

    @Test
    public void competitions() throws Exception {
        Call<List<Competition>> call = footballData.competitions();
        Response<List<Competition>> response = call.execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List competitions = response.body();
        Assert.assertEquals("Returns all competitions", 15, competitions.size());
    }

    @Test
    public void teamsForCompetition() throws Exception {
        Response<ArrayList<Team>> response = footballData.teamsForCompetition(PL_2016).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List competitions = response.body();
        Assert.assertEquals("Returns all teams", 20, competitions.size());
    }

    @Test
    public void leagueTableForCompetition() throws Exception {
        Response<LeagueTable> response = footballData.leagueTableForCompetition(PL_2016).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        LeagueTable leagueTable = response.body();
        Assert.assertEquals("Returns all teams", 20, leagueTable.getStanding().size());
    }

    @Test
    public void fixtures() throws Exception {
        Response<ArrayList<Fixture>> response = footballData.fixtures().execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List<Fixture> fixtures = response.body();
        Assert.assertEquals("Returns all fixtures", 150, fixtures.size());
    }

    @Test
    public void gamePreview() throws Exception {
        Response<GamePreview> response = footballData.gamePreview(BRAZIL_VS_GERMANY).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());
    }

    @Test
    public void fixturesForCompetition() throws Exception {
        Response<ArrayList<Fixture>> response = footballData.fixturesForCompetition(PL_2016).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List<Fixture> fixtures = response.body();
        Assert.assertEquals("Returns all fixtures", 380, fixtures.size());
    }

    @Test
    public void fixturesForTeam() throws Exception {
        Response<ArrayList<Fixture>> response = footballData.fixturesForTeam(ARSENAL_FC).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List<Fixture> fixtures = response.body();
        Assert.assertEquals("Returns all fixtures", 150, fixtures.size());
    }

    @Test
    public void team() throws Exception {
        Response<Team> response = footballData.team(ARSENAL_FC).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        Team team = response.body();
        Assert.assertEquals("Returns correct id", 57, team.getId());
        Assert.assertEquals("Returns correct name", "Arsenal", team.getShortName());

    }

    @Test
    public void playersForTeam() throws Exception {
        Response<ArrayList<Player>> response = footballData.playersForTeam(ARSENAL_FC).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("Returns 200", 200, response.code());

        List<Player> arsenalSquad = response.body();
        Assert.assertEquals("Returns all players", 29, arsenalSquad.size());
    }

}