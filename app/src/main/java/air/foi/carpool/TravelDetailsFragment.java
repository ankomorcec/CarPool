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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ankomorcec on 30.12.2015..
 * Prikaz detalja putovanja uz mogućnosti Rezerviranja putovanja
 * i uklanjanja rezervacije
 */
public class TravelDetailsFragment extends Fragment {
    @BindView(R.id.travel_details_name) TextView name;
    @BindView(R.id.travel_details_description) TextView description;
    @BindView(R.id.travel_details_start) TextView startDate;
    @BindView(R.id.pass_list) ListView listView;
    @BindView(R.id.ReserveTravelButton) Button reserveTravel;
    @BindView(R.id.RemoveTravelButton) Button removeTravel;

    Bundle data;
    long id;
    Travel t;

    @OnClick(R.id.ReserveTravelButton)
    public void reserveClick() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String currentUser=settings.getString("username", null);
        String currentUserId = settings.getString("user_id", null);
        Toast.makeText(getActivity(), Passenger.addPassenger(currentUserId, t.getId().toString()), Toast.LENGTH_SHORT).show();
        getPassangers();
    }

    @OnClick(R.id.RemoveTravelButton)
    public void removeClick() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String currentUser=settings.getString("username", null);
        String currentUserId = settings.getString("user_id", null);
        Toast.makeText(getActivity(), Passenger.removePassenger(currentUserId, t.getId().toString()), Toast.LENGTH_SHORT).show();
        getPassangers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // get Bundle and extra data sent from caller activity
        data = getArguments();
        id = data.getLong("id");
        t = new Select().from(Travel.class).where("Id == ?", id).executeSingle();

        name.setText(t.getStartPoint() + " - " + t.getEndPoint());

        description.setText("Creator: " + t.getUser());

        startDate.setText("Departure: " + t.getStartTime());

        getPassangers();

        reserveTravel.setText(R.string.reserve_travel);

        removeTravel.setText(R.string.unreserve_travel);

    }

    /**
     * Dohvat putnika kao posebna funkcija
     * zbog potrebe osvježivanja pregleda
     */
    public void getPassangers(){
                 /*Get passengers*/

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
    }
}
