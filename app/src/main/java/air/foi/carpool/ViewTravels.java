package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Kiwi on 11/15/2015.
 */
public class ViewTravels extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_travels, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button addTravel = (Button) getView().findViewById(R.id.AddTravelButton);
        addTravel.setText(R.string.add_travel);
        addTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AddNewTravel vt = new AddNewTravel();
                    FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                    fm.replace(R.id.fragment_container, vt);
                    fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fm.addToBackStack("viewTravelsFragment");
                    fm.commit();
            }
        });
    }
}
