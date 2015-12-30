package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import air.foi.db.Travel;

/**
 * Created by Kiwi on 11/15/2015.
 */
public class ViewTravels extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_travels, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        ListView listView = (ListView) getView().findViewById(R.id.travel_list);

        ArrayList<Travel> travels = Travel.getAll();

        travelAdapter adapter = new travelAdapter(getActivity().getApplicationContext(), travels);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {

                                                String recId = ((TextView)view.findViewById(R.id.recordId)).getText().toString();

                                                Bundle args = new Bundle();
                                                args.putLong("id", (Long) Long.parseLong(recId));

                                                TravelDetailsFragment tdf = new TravelDetailsFragment();
                                                tdf.setArguments(args);

                                                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                                                fm.replace(R.id.fragment_container, tdf);
                                                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                                fm.addToBackStack("viewTravelsFragment");
                                                fm.commit();
                                            }
                                        }
        );

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
