package no.philipp.soccerdata;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.models.Fixture;
import no.philipp.footballdata.models.Result;

/**
 * Created by andyphilipp on 24/11/2016.
 */
public class FixtureAdapter extends BaseAdapter {
    java.text.DateFormat dateFormat;
    private List<Fixture> mFixtures;
    private Context mContext;

    public FixtureAdapter(Context context, List<Fixture> fixtures) {
        super();
        mFixtures = fixtures;
        mContext = context;
        Collections.sort(fixtures, Fixture.DATE_COMPARATOR);
        dateFormat = DateFormat.getDateFormat(getContext()).getDateTimeInstance(java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT);
    }

    public int getCurrentPosition() {
        Fixture hypotheticFixture = new Fixture();
        hypotheticFixture.setDate(new Date());
        return -Collections.binarySearch(mFixtures, hypotheticFixture, Fixture.DATE_COMPARATOR) - 2;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getCount() {
        return mFixtures.size();
    }

    @Override
    public Fixture getItem(int i) {
        return mFixtures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Fixture fixture = getItem(position);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_fixture, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Result result = fixture.getResult();
        String homeScore = result.getGoalsHomeTeam() == null ? "-" : Integer.toString(result.getGoalsHomeTeam());
        String awayScore = result.getGoalsAwayTeam() == null ? "-" : Integer.toString(result.getGoalsAwayTeam());

        holder.homeTeam.setText(fixture.getHomeTeamName());
        holder.awayTeam.setText(fixture.getAwayTeamName());
        holder.homeScore.setText(homeScore);
        holder.awayScore.setText(awayScore);
        holder.status.setText(fixture.getStatus().toString());
        holder.date.setText(dateFormat.format(fixture.getDate()));
        if(fixture.getStatus() == Fixture.Status.FINISHED){
            view.setBackgroundColor(Color.parseColor("#e0e0e0"));
        }else{
            view.setBackgroundColor(Color.WHITE);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.fixture_home_score)
        TextView homeScore;
        @BindView(R.id.fixture_home_team)
        TextView homeTeam;
        @BindView(R.id.fixture_away_score)
        TextView awayScore;
        @BindView(R.id.fixture_away_team)
        TextView awayTeam;
        @BindView(R.id.fixture_status)
        TextView status;
        @BindView(R.id.fixture_date)
        TextView date;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
