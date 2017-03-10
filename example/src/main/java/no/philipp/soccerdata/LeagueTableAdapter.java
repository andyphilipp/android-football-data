package no.philipp.soccerdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.models.Standing;

/**
 * Adapter that lists the standings of a league table,
 * sorted on the league table position.
 */
public class LeagueTableAdapter extends ArrayAdapter<Standing> {
    public LeagueTableAdapter(Context context, List<Standing> objects) {
        super(context, R.layout.item_standing, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Standing standing = getItem(position);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_standing, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.position.setText(Integer.toString(standing.getPosition()));
        holder.team.setText(standing.getTeamName());
        holder.points.setText(Integer.toString(standing.getPoints()));
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.standing_position)
        TextView position;
        @BindView(R.id.standing_team)
        TextView team;
        @BindView(R.id.standing_points)
        TextView points;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
