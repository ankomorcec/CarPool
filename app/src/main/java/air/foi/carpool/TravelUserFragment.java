package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import air.foi.db.Travel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by antoniok on 28.8.2016..
 */
public class TravelUserFragment extends Fragment {
    @BindView(R.id.AddTravelButton) Button addTravel;
    @BindView(R.id.travel_user_list) ListView listView;

    @OnClick(R.id.AddTravelButton)
    public void clickAddTravel(){
        AddNewTravel vt = new AddNewTravel();
        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, vt);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("viewTravelsFragment");
        fm.commit();
    }

    @OnItemClick(R.id.travel_user_list)
    public void clickTravelList(View view){
        String recId = ((TextView)view.findViewById(R.id.travelValue)).getText().toString();

        Bundle args = new Bundle();
        args.putString("user", recId);

        TravelByUsersFragment tdf = new TravelByUsersFragment();
        tdf.setArguments(args);

        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, tdf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("viewTravelsFragment");
        fm.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_travel_users, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<Travel> travelsList = Travel.getUsers();
        ArrayList<Travel> travels = new ArrayList<>(travelsList.size());
        travels.addAll(travelsList);

        TravelAdapter adapter = new TravelAdapter(getActivity().getApplicationContext(), travels);

        adapter.setUsers();

        listView.setAdapter(adapter);

        addTravel.setText(R.string.add_travel);

    }
}
