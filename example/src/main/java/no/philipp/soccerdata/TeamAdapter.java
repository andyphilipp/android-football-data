package no.philipp.soccerdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.models.Team;
import no.philipp.soccerdata.svg.SvgLoader;

/**
 * Adapter for a list of {@link Team}s.
 */
public class TeamAdapter extends ArrayAdapter<Team> {
    SvgLoader mSvgLoader;

    public TeamAdapter(Context context, List<Team> objects) {
        super(context, R.layout.item_team, objects);
        mSvgLoader = new SvgLoader(context);
        Collections.sort(objects, Team.NAME_COMPARATOR);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Team team = getItem(position);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_team, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.teamName.setText(team.getName());
        mSvgLoader.load(team.getCrestUrl().toString(), holder.teamLogo, R.drawable.pl_logo);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.team_name)
        TextView teamName;
        @BindView(R.id.team_logo)
        ImageView teamLogo;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
