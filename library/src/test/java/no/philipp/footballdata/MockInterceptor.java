package no.philipp.footballdata;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import no.philipp.footballdata.models.Competition;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by andyphilipp on 22/11/2016.
 */
class MockInterceptor implements Interceptor {
    private static final int PL_2016 = 426;
    private static final int ARSENAL_FC = 57;
    private static final int BRAZIL_VS_GERMANY = 149461;

    private String readJson(String filename) throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource(filename).getPath());
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().encodedPath();
        Response.Builder responseBuilder = new Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .addHeader("content-type", "application/json");

        System.out.println("MockInterceptor " + url);
        String responseString;
        if (url.endsWith("/competitions")) {
            responseString = readJson("json/competitions.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/competitions/" + PL_2016 + "/teams")) {
            responseString = readJson("json/teams_for_competition.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/competitions/" + PL_2016 + "/fixtures")) {
            responseString = readJson("json/fixtures_for_competition.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/competitions/" + PL_2016 + "/leagueTable")) {
            responseString = readJson("json/league_table_for_competition.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/teams/" + ARSENAL_FC)) {
            responseString = readJson("json/team.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/teams/" + ARSENAL_FC + "/players")) {
            responseString = readJson("json/players_for_team.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/fixtures/" + BRAZIL_VS_GERMANY)) {
            responseString = readJson("json/players_for_team.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/fixtures")) {
            responseString = readJson("json/fixtures.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else if (url.endsWith("/teams/" + ARSENAL_FC + "/fixtures")) {
            responseString = readJson("json/fixtures.json");
            responseBuilder.code(200)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()));
        } else {
            responseBuilder.code(404);
        }

        return responseBuilder.build();
    }
}
