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
import no.philipp.footballdata.models.Player;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Lists all player of a team.
 */
public class SquadFragment extends ListFragment {
    private FootballData mFootballData;

    private int mTeamId;
    private List<Player> mPlayers;

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
    }

    private void loadData() {
        mFootballData.playersForTeam(mTeamId).enqueue(new Callback<ArrayList<Player>>() {
            @Override
            public void onResponse(Call<ArrayList<Player>> call, Response<ArrayList<Player>> response) {
                mPlayers = response.body();
                showData();
            }

            @Override
            public void onFailure(Call<ArrayList<Player>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        setListAdapter(new PlayerAdapter(getActivity(), mPlayers));
    }

    /**
     * Creates a new SquadFragment for the given team id.
     */
    public static Fragment newInstance(int teamId) {
        SquadFragment fragment = new SquadFragment();
        fragment.mTeamId = teamId;
        return fragment;
    }
}
