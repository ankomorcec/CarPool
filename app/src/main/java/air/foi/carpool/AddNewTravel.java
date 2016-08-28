package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import air.foi.db.Travel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kiwi on 12/30/2015.
 */
public class AddNewTravel extends Fragment {
    @BindView(R.id.add_new_travel_button) Button addNewTravelButton;

    String[] startPoints = {"",""};
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    Double start_lat = 0.00;
    Double start_long = 0.00;
    Double end_lat = 0.00;
    Double end_long = 0.00;

    @OnClick(R.id.add_new_travel_button)
    public void clickAddnew(){
        String startPoint = "";
        if (startPoints[0] != null)
            startPoint = startPoints[0];

        String endPoint = "";
        if (startPoints[1] != null)
            endPoint = startPoints[1];
        String departTime = ((TextView) getActivity().findViewById(R.id.depart_time_edit)).getText().toString();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String username = settings.getString("username", null);

        if (!(startPoint.equals("") && endPoint.equals("") && username.equals(null))) {

            float[] results = new float[1];

            Location.distanceBetween(start_lat,start_long,end_lat,end_long,results);
            Log.i("DISTANCE", "Udaljeno: " + results[0]);//get place details here

            Travel travel = new Travel(startPoint, endPoint, departTime, username, start_lat, start_long,end_lat,end_long,results[0]);
            travel.save();

            ChooseListingType vt = new ChooseListingType();
            FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, vt);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("addNewTravel");
            fm.commit();
        } else {
            Toast.makeText(getActivity(), R.string.login_fail, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_travel, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // autocomplete places - START
        PlaceAutocompleteFragment autocompleteFragmentFrom = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.starting_point_edit);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        autocompleteFragmentFrom.setFilter(typeFilter);


        autocompleteFragmentFrom.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("MJESTO", "Place: " + place.getName());//get place details here
                startPoints[0] = place.getAddress().toString();
                start_lat = place.getLatLng().latitude;
                start_long = place.getLatLng().longitude;

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("GRESKA", "An error occurred: " + status);
            }
        });

        PlaceAutocompleteFragment autocompleteFragmentTo = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.end_point_edit);

        AutocompleteFilter typeFilter2 = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        autocompleteFragmentTo.setFilter(typeFilter2);


        autocompleteFragmentTo.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("MJESTO", "Place: " + place.getName());//get place details here
                startPoints[1] = place.getAddress().toString();
                end_lat = place.getLatLng().latitude;
                end_long = place.getLatLng().longitude;

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("GRESKA", "An error occurred: " + status);
            }
        });

        // autocomplete places - END

        addNewTravelButton.setText(R.string.add_new_travel_button);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PlaceAutocompleteFragment autocompleteFragmentTo = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.end_point_edit);
        if (autocompleteFragmentTo != null)
            getFragmentManager().beginTransaction().remove(autocompleteFragmentTo).commit();

        PlaceAutocompleteFragment autocompleteFragmentFrom = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.starting_point_edit);
        if (autocompleteFragmentFrom != null)
            getFragmentManager().beginTransaction().remove(autocompleteFragmentFrom).commit();
    }
}
