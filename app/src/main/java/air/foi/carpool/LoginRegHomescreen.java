package air.foi.carpool;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginRegHomescreen extends Fragment {
    @BindView(R.id.LoginNavButton) Button login;
    @BindView(R.id.RegisterNavButton) Button register;



    @OnClick(R.id.LoginNavButton)
    public void loginClick() {
        LoginFragment lf = new LoginFragment();

        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, lf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("loginHome");
        fm.commit();
    }

    @OnClick(R.id.RegisterNavButton)
    public void registerClick () {
        RegisterFragment rf = new RegisterFragment();

        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, rf);
        fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.addToBackStack("registerHome");
        fm.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_reg_homescreen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        login.setText(R.string.home_login_button);


        register.setText(R.string.home_reg_button);


    }

}
