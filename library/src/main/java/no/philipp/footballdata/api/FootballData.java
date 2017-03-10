package no.philipp.footballdata.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import no.philipp.footballdata.models.Competition;
import no.philipp.footballdata.models.Fixture;
import no.philipp.footballdata.models.GamePreview;
import no.philipp.footballdata.models.LeagueTable;
import no.philipp.footballdata.models.Player;
import no.philipp.footballdata.models.Team;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andyphilipp on 22/11/2016.
 */
public class FootballData {
    public final static String BASE_URL = "http://api.football-data.org/v1/";
    private static String API_KEY = null;
    private static FootballData instance;
    private static FootballDataService service;

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static FootballData getInstance() {
        return getInstance(null);
    }

    public static FootballData getInstance(Interceptor interceptor) {
        if (instance == null) {
            if (API_KEY == null) {
                try {
                    Log.w("FootballData", "API key not set, using limited public API.");
                } catch (RuntimeException e) {
                    System.out.println("WARNING: API key not set.");
                }
            }

            // Initialize
            Interceptor apiKeyinterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();
                    builder.removeHeader("User-Agent")
                            .addHeader("User-Agent", "no.philipp.footballdata");
                    if (API_KEY != null) {
                        builder.addHeader("X-Auth-Token", API_KEY);
                    }
                    return chain.proceed(builder.build());
                }
            };

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(apiKeyinterceptor);

            if (interceptor != null) {
                httpClientBuilder.addInterceptor(interceptor);
            }

            OkHttpClient client = httpClientBuilder.build();

            JsonDeserializer wrappedListDeserializer = new JsonDeserializer<ArrayList>() {
                @Override
                public ArrayList deserialize(
                        JsonElement json,
                        Type typeOfT,
                        JsonDeserializationContext context) throws JsonParseException {
                    JsonObject wrapper = json.getAsJsonObject();
                    JsonElement countElement = wrapper.get("count");
                    if (countElement.isJsonNull()) {
                        //TODO normal List parsing
                        return context.deserialize(json, List.class);
                    }
                    int count = countElement.getAsInt();
                    if (wrapper.has("teams")) {
                        return getArrayList(wrapper.get("teams"), count, Team.class, context);
                    } else if (wrapper.has("players")) {
                        return getArrayList(wrapper.get("players"), count, Player.class, context);
                    } else {
                        return getArrayList(wrapper.get("fixtures"), count, Fixture.class, context);
                    }
                }

                private ArrayList getArrayList(
                        JsonElement jsonElement,
                        int count, Class clazz,
                        JsonDeserializationContext context) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    ArrayList list = new ArrayList(count);
                    for (JsonElement element : jsonArray) {
                        list.add(context.deserialize(element, clazz));
                    }
                    return list;
                }
            };

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Team.class, new TeamDeserializer())
                    .registerTypeAdapter(ArrayList.class, wrappedListDeserializer)
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            service = retrofit.create(FootballDataService.class);

            instance = new FootballData();
        }
        return instance;
    }

    private FootballData() {
        /* Singleton */
    }

    public Call<List<Competition>> competitions() {
        return service.competitions();
    }

    public Call<ArrayList<Team>> teamsForCompetition(int competitionId) {
        return service.teamsForCompetition(competitionId);
    }

    public Call<LeagueTable> leagueTableForCompetition(int competitionId) {
        return service.leagueTableForCompetition(competitionId);
    }

    public Call<ArrayList<Fixture>> fixtures() {
        return service.fixtures();
    }

    public Call<GamePreview> gamePreview(int fixtureId) {
        return service.gamePreview(fixtureId);
    }

    public Call<ArrayList<Fixture>> fixturesForCompetition(int competitionId) {
        return service.fixturesForCompetition(competitionId);
    }

    public Call<ArrayList<Fixture>> fixturesForTeam(int teamId) {
        return service.fixturesForTeam(teamId);
    }

    public Call<Team> team(int teamId) {
        return service.team(teamId);
    }

    public Call<ArrayList<Player>> playersForTeam(int teamId) {
        return service.playersForTeam(teamId);
    }
}
