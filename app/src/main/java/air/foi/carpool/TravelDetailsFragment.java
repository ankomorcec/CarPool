package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import air.foi.db.Travel;
import air.foi.db.Passenger;

/**
 * Created by ankomorcec on 30.12.2015..
 */
public class TravelDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.travel_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // get Bundle and extra data sent from caller activity
        Bundle data = getArguments();
        long id = data.getLong("id");

        final Travel t = new Select().from(Travel.class).where("Id == ?", id).executeSingle();


        TextView name = ((TextView) getView().findViewById(R.id.travel_details_name));
        name.setText(t.getStartPoint() + " - " + t.getEndPoint());

        TextView description = ((TextView) getView().findViewById(R.id.travel_details_description));
        description.setText("Creator: " + t.getUser());

        TextView startDate = ((TextView) getView().findViewById(R.id.travel_details_start));
        startDate.setText("Departure: " + t.getStartTime());

         /*Get passengers*/
        ListView listView = (ListView) getView().findViewById(R.id.pass_list);

        List<String> passList = Passenger.getTravelPassengers(t.getId().toString());
        ArrayList<String> passengers = new ArrayList<>(passList.size());
        passengers.addAll(passList);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                passengers){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                text.setTextSize(15);
                return view;
            }
        };

        listView.setAdapter(adapter);
        /*get passengers end*/

        Button reserveTravel = (Button) getView().findViewById(R.id.ReserveTravelButton);
        reserveTravel.setText(R.string.reserve_travel);
        reserveTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                String currentUser=settings.getString("username", null);
                String currentUserId = settings.getString("user_id", null);
                Toast.makeText(getActivity(), Passenger.addPassenger(currentUserId, t.getId().toString()), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
