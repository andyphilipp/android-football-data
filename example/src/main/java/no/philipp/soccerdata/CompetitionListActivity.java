package no.philipp.soccerdata;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Competition;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andyphilipp on 29/11/2016.
 */
public class CompetitionListActivity extends ListActivity {


    private FootballData footballData;
    private List<Competition> competitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footballData = FootballData.getInstance();
        loadData();
    }

    private void loadData() {
        footballData.competitions().enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                competitions = response.body();
                showData();
            }

            @Override
            public void onFailure(Call<List<Competition>> call, Throwable t) {

            }
        });
    }

    private void showData() {
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, competitions));
    }
}
