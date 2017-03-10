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

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.api.FootballData;
import no.philipp.footballdata.models.Competition;
import no.philipp.soccerdata.svg.SvgLoader;

public class CompetitionActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_LOGO = 20;
    public static int LEAGUE_ID = 426;
    public static String TROPHY_LOGO_URL = "http://www.babson.edu/PublishingImages/homepage/facts/trophy-blue.svg";

    private boolean mIsLogoShown = true;
    private int mMaxScrollSize;
    private FootballData mFootballData;
    private SvgLoader mSvgLoader;

    private Competition mCompetition;

    @BindView(R.id.competition_tabs)
    TabLayout tabLayout;
    @BindView(R.id.competition_viewpager)
    ViewPager viewPager;
    @BindView(R.id.competition_appbar)
    AppBarLayout appbarLayout;
    @BindView(R.id.competition_toolbar)
    Toolbar toolbar;
    @BindView(R.id.competition_logo)
    ImageView mLogo;
    @BindView(R.id.competition_title)
    TextView mTitle;
    @BindView(R.id.competition_subtitle)
    TextView mSubTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
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

        loadData();
    }

    public void loadData() {
        showData();
    }

    public void showData() {
        mLogo.setImageResource(R.drawable.pl_logo);
    }

    public static void start(Context c, int leagueId) {
        CompetitionActivity.LEAGUE_ID = leagueId;
        c.startActivity(new Intent(c, CompetitionActivity.class));
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
                    return LeagueTableFragment.newInstance(LEAGUE_ID);
                case 1:
                    return FixturesFragment.newInstance(FixturesFragment.Mode.COMPETITION, LEAGUE_ID);
                case 2:
                    return TeamListFragment.newInstance(LEAGUE_ID);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Standings";
                case 1:
                    return "Fixtures";
                case 2:
                    return "Teams";
            }
            return "";
        }
    }

}
