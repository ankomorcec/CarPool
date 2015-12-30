package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import air.foi.db.Travel;

/**
 * Created by Kiwi on 12/30/2015.
 */
public class AddNewTravel extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_new_travel, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button addNewTravelButton = (Button) getView().findViewById(R.id.add_new_travel_button);
        addNewTravelButton.setText(R.string.add_new_travel_button);
        addNewTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startPoint = ((TextView) getActivity().findViewById(R.id.starting_point_edit)).getText().toString();
                String endPoint = ((TextView) getActivity().findViewById(R.id.end_point_edit)).getText().toString();
                String departTime = ((TextView) getActivity().findViewById(R.id.depart_time_edit)).getText().toString();
                if (!(startPoint.equals("") && endPoint.equals(""))) {
                    Travel travel = new Travel(startPoint, endPoint, departTime);
                    travel.save();

                    ViewTravels vt = new ViewTravels();
                    FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                    fm.replace(R.id.fragment_container, vt);
                    fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fm.addToBackStack("addNewTravelFragment");
                    fm.commit();
                } else {
                    Toast.makeText(getActivity(), R.string.login_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
