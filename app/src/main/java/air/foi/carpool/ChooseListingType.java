package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
    FragmentDataDisplay frgDataDisplay;


    @OnClick(R.id.ListNavButton)
    public void loginClick() {

        //Fragment lf = frgDataDisplay.getFragment("MAP");

        //TabbedTravelDetFragment lf = new TabbedTravelDetFragment();
        Fragment lf = null;
            try {
                lf = checkGetFragment("air.foi.carpool.MapFragment", true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }

        if (lf != null) {
            FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, lf);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("chooseType");
            fm.commit();
        }
    }

    @OnClick(R.id.MapNavButton)
    public void mapViewClick() {
        Fragment mf = null;
        try {
            mf = checkGetFragment("air.foi.carpool.TabbedTravelDetFragment", false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        if(mf != null) {
            FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, mf);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("chooseType");
            fm.commit();
        }
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
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String strBtn1 =settings.getString("btn1", null);
        String strBtn2 = settings.getString("btn2", null);

        if (strBtn1 != null)
            login.setText(strBtn1);
        else
            login.setText(R.string.list_type_button);

        if (strBtn2 != null)
            register.setText(strBtn2);
        else
            register.setText(R.string.map_type_button);

        btnStatistic.setText("Statistic");

    }

    public Fragment checkGetFragment(String className, boolean prvi) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, java.lang.InstantiationException {
        Class<?> c = Class.forName(className);
        Constructor<?> cons = c.getConstructors()[0];
        Object object = cons.newInstance();

        try{
            frgDataDisplay = (FragmentDataDisplay) object;
        } catch (ClassCastException e) {
            throw new ClassCastException(object.toString()
                    + " nije implementirano sucelje!");
        }

        if(frgDataDisplay != null) {
            setBtnLabel(frgDataDisplay.getBtnCaption(),prvi);
            return frgDataDisplay.getFragment();
        }
        else
            return null;
    }

    public void setBtnLabel(String label, boolean prvi) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        if (prvi)
            editor.putString("btn1", label);
        else
            editor.putString("btn2", label);
        editor.commit();
    }

}
