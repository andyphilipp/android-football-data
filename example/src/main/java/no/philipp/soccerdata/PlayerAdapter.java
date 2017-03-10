package no.philipp.soccerdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.philipp.footballdata.models.Player;

/**
 * Adapter for a team squad.
 */
public class PlayerAdapter extends ArrayAdapter<Player> {

    public PlayerAdapter(Context context, List<Player> objects) {
        super(context, R.layout.item_fixture, objects);
        Collections.sort(objects, Player.JERSEY_NUMBER_COMPARATOR);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        Player player = getItem(position);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_player, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.jerseyNumber.setText(Integer.toString(player.getJerseyNumber()));
        holder.name.setText(player.getName());
        holder.position.setText(player.getPosition() == null ? "-" : player.getPosition().getValue());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.player_jersey_number)
        TextView jerseyNumber;
        @BindView(R.id.player_name)
        TextView name;
        @BindView(R.id.player_position)
        TextView position;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
