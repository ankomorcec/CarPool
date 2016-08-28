package air.foi.carpool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import air.foi.db.Travel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kiwi on 12/30/2015.
 */
public class TravelAdapter extends ArrayAdapter<Travel> {
    public TravelAdapter(Context context, ArrayList<Travel> users) {
        super(context, 0, users);
    }

    @BindView(R.id.travelValue) TextView travelVal;
    @BindView(R.id.recordId) TextView travelId;

    public boolean boolUsers = false;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Travel travel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
            ButterKnife.bind(this,convertView);
        }

        if (boolUsers)
            travelVal.setText(travel.user);
        else {
            travelVal.setText(travel.startPoint + "\n-> " + travel.endPoint + " (" + travel.startTime + ")");
            travelId.setText(travel.getId().toString());
        }
        return convertView;
    }

    public void setUsers() {
        boolUsers = true;
    }

}