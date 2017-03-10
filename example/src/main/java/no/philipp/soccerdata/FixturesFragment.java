package no.philipp.soccerdata;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Fixture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Lists fixtures for a team or competition.
 */
public class FixturesFragment extends ListFragment {
    public enum Mode {TEAM, COMPETITION}

    private FootballData mFootballData;

    private List<Fixture> mFixtures;
    private Mode mMode;
    private int mId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFootballData = FootballData.getInstance();
        loadData();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT > 21) {
            getListView().setNestedScrollingEnabled(true);
        }
    }

    private void loadData() {
        if(mMode == Mode.TEAM){
            mFootballData.fixturesForTeam(mId).enqueue(new Callback<ArrayList<Fixture>>() {
                @Override
                public void onResponse(Call<ArrayList<Fixture>> call, Response<ArrayList<Fixture>> response) {
                    mFixtures = response.body();
                    showData();
                }

                @Override
                public void onFailure(Call<ArrayList<Fixture>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (mMode == Mode.COMPETITION){
            mFootballData.fixturesForCompetition(mId).enqueue(new Callback<ArrayList<Fixture>>() {
                @Override
                public void onResponse(Call<ArrayList<Fixture>> call, Response<ArrayList<Fixture>> response) {
                    mFixtures = response.body();
                    showData();
                }

                @Override
                public void onFailure(Call<ArrayList<Fixture>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showData() {
        FixtureAdapter adapter = new FixtureAdapter(getActivity(), mFixtures);
        setListAdapter(adapter);
        getListView().setSelection(adapter.getCurrentPosition());
    }

    /**
     * Create a new FixturesFragment instance.
     *
     * @param mode indicates if the id is liked to a team or a competition.
     * @param id the id for the team or competition.
     */
    public static Fragment newInstance(Mode mode, int id) {
        FixturesFragment fragment = new FixturesFragment();
        fragment.mMode = mode;
        fragment.mId = id;
        return fragment;
    }
}
