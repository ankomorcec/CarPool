package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kiwi on 22.8.2016..
 */
public class ChooseListingType extends Fragment {
    @BindView(R.id.ListNavButton) Button login;
    @BindView(R.id.MapNavButton) Button register;
    @BindView(R.id.StatButton) Button btnStatistic;


    @OnClick(R.id.ListNavButton)
    public void loginClick() {
        TabbedTravelDetFragment lf = new TabbedTravelDetFragment();

        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, lf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("chooseType");
        fm.commit();
    }

    @OnClick(R.id.MapNavButton)
    public void mapViewClick() {
        MapFragment mf = new MapFragment();
        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, mf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("chooseType");
        fm.commit();
    }

    @OnClick(R.id.StatButton)
    public void clickStat() {
        StatisticFragment sf = new StatisticFragment();
        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, sf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("chooseType");
        fm.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.choose_listing_type_layout, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        login.setText(R.string.list_type_button);

        register.setText(R.string.map_type_button);

        btnStatistic.setText("Statistic");

    }

}
