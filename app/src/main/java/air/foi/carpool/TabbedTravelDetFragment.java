package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Created by antoniok on 28.8.2016..
 * Fragment kao poslužitelj tabulranog prikaza dva druga Fragmenta
 */
public class TabbedTravelDetFragment extends Fragment implements FragmentDataDisplay {
    private FragmentTabHost ftHost;

    public TabbedTravelDetFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_travel_det_layout,container, false);
        ButterKnife.bind(this,rootView);


        ftHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        ftHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        ftHost.addTab(ftHost.newTabSpec("fragmenta").setIndicator("Travels"),
                ViewTravels.class, null);
        ftHost.addTab(ftHost.newTabSpec("fragmentb").setIndicator("Users"),
                TravelUserFragment.class, null);

        return rootView;
    }

    public Fragment newInstance(){
        TabbedTravelDetFragment tabbedTrvlFrgmnt = new TabbedTravelDetFragment();
        return tabbedTrvlFrgmnt;
    }

    @Override
    public Fragment getFragment() {
            return this;
    }

    @Override
    public String getBtnCaption() {
        return "List of travels";
    }
}
