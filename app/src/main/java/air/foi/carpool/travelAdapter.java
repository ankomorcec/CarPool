package air.foi.carpool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import air.foi.db.Travel;

/**
 * Created by Kiwi on 12/30/2015.
 */
public class travelAdapter extends ArrayAdapter<Travel> {
    public travelAdapter(Context context, ArrayList<Travel> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Travel travel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView travelVal = (TextView) convertView.findViewById(R.id.travelValue);
        TextView travelId = (TextView) convertView.findViewById(R.id.recordId);

        travelVal.setText(travel.startPoint+"\n-> "+travel.endPoint + " ("+travel.startTime+")");
        travelId.setText(travel.getId().toString());

        return convertView;
    }
}