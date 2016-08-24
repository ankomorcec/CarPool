package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Kiwi on 22.8.2016..
 */
public class choose_listing_type extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_listing_type_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button login = (Button) getView().findViewById(R.id.ListNavButton);
        login.setText(R.string.list_type_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewTravels lf = new ViewTravels();

                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, lf);
                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.addToBackStack("chooseType");
                fm.commit();
            }
        });

        Button register = (Button) getView().findViewById(R.id.MapNavButton);
        register.setText(R.string.map_type_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment mf = new MapFragment();
                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, mf);
                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.addToBackStack("chooseType");
                fm.commit();
            }
        });

    }

}
