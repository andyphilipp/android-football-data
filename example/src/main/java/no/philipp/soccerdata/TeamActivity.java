package no.philipp.soccerdata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Team;
import no.philipp.soccerdata.svg.SvgLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_LOGO = 20;
    private static final String KEY_TEAM_ID = "teamId";
    private static final int DEFAULT_TEAM_ID = 57;

    private int mTeamId = DEFAULT_TEAM_ID;

    private FootballData mFootballData;
    private SvgLoader mSvgLoader;

    private boolean mIsLogoShown = true;
    private int mMaxScrollSize;
    private Team team;

    @BindView(R.id.tam_tabs)
    TabLayout tabLayout;
    @BindView(R.id.team_viewpager)
    ViewPager viewPager;
    @BindView(R.id.team_appbar)
    AppBarLayout appbarLayout;
    @BindView(R.id.team_toolbar)
    Toolbar toolbar;
    @BindView(R.id.team_logo)
    ImageView mLogo;
    @BindView(R.id.team_title)
    TextView mTitle;
    @BindView(R.id.team_subtitle)
    TextView mSubTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        mFootballData = FootballData.getInstance();
        mSvgLoader = new SvgLoader(this);
        mTeamId = getIntent().getIntExtra(KEY_TEAM_ID, DEFAULT_TEAM_ID);
        loadData();
    }

    public void loadData() {
        mFootballData.team(mTeamId).enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                team = response.body();
                showData();
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(TeamActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showData() {
        mSvgLoader.load(team.getCrestUrl().toString(), mLogo, R.drawable.pl_logo);
        mTitle.setText(team.getName());
        mSubTitle.setText(team.getSquadMarketValue());
    }

    public static void start(Context c, int teamId) {
        Intent intent = new Intent(c, TeamActivity.class);
        intent.putExtra(KEY_TEAM_ID, teamId);
        c.startActivity(intent);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_LOGO && mIsLogoShown) {
            mIsLogoShown = false;
            mLogo.animate().scaleY(0).scaleX(0).setDuration(200).start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_LOGO && !mIsLogoShown) {
            mIsLogoShown = true;

            mLogo.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return FixturesFragment.newInstance(FixturesFragment.Mode.TEAM, mTeamId);
                case 1:
                    return LeagueTableFragment.newInstance(CompetitionActivity.LEAGUE_ID);
                case 2:
                    return SquadFragment.newInstance(mTeamId);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Fixtures";
                case 1:
                    return "Standings";
                case 2:
                    return "Squad";
            }
            return "";
        }
    }

}
