package no.philipp.soccerdata;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.LeagueTable;
import no.philipp.footballdata.models.Standing;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Lists the current standings for a competition.
 */
public class LeagueTableFragment extends ListFragment {
    private FootballData mFootballData;

    private int mCompetitionId;
    private List<Standing> mLeagueTableRows;

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
        mFootballData.leagueTableForCompetition(mCompetitionId).enqueue(new Callback<LeagueTable>() {
            @Override
            public void onResponse(Call<LeagueTable> call, Response<LeagueTable> response) {
                Log.v("Test", response.message());
                mLeagueTableRows = response.body().getStanding();
                showData();
            }

            @Override
            public void onFailure(Call<LeagueTable> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        setListAdapter(new LeagueTableAdapter(getActivity(), mLeagueTableRows));
    }

    /**
     * Creates a new LeagueTableFragment for the given competition id
     */
    public static Fragment newInstance(int competitionId) {
        LeagueTableFragment fragment = new LeagueTableFragment();
        fragment.mCompetitionId = competitionId;
        return fragment;
    }
}
