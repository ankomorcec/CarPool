package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import air.foi.db.Travel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by antoniok on 28.8.2016..
 */
public class TravelByUsersFragment extends Fragment {
    @BindView(R.id.travel_list_by_user) ListView listView;


    @OnItemClick(R.id.travel_list_by_user)
    public void clickTravelList(View view){
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_travels_by_user, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // get Bundle and extra data sent from caller activity
        Bundle data = getArguments();
        String id = data.getString("user");

        List<Travel> travelsList = Travel.getTravelsByUser(id);
        ArrayList<Travel> travels = new ArrayList<>(travelsList.size());
        travels.addAll(travelsList);

        TravelAdapter adapter = new TravelAdapter(getActivity().getApplicationContext(), travels);

        listView.setAdapter(adapter);


    }
}
