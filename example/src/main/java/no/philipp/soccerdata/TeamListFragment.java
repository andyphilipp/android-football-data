package no.philipp.soccerdata;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Lists all teams for a competition.
 */
public class TeamListFragment extends ListFragment {
    private FootballData mFootballData;
    private List<Team> mTeams;
    private int mCompetitionId;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT > 21) {
            getListView().setNestedScrollingEnabled(true);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFootballData = FootballData.getInstance();
        loadData();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long teamId) {
                TeamActivity.start(getActivity(), (int) teamId);
            }
        });
    }

    private void loadData() {
        mFootballData.teamsForCompetition(mCompetitionId).enqueue(new Callback<ArrayList<Team>>() {
            @Override
            public void onResponse(Call<ArrayList<Team>> call, Response<ArrayList<Team>> response) {
                mTeams = response.body();
                showData();
            }

            @Override
            public void onFailure(Call<ArrayList<Team>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        setListAdapter(new TeamAdapter(getActivity(), mTeams));
    }

    /**
     * Creates a TeamListFragment for a given competition id.
     */
    public static Fragment newInstance(int competitionId) {
        TeamListFragment fragment = new TeamListFragment();
        fragment.mCompetitionId = competitionId;
        return fragment;
    }
}
