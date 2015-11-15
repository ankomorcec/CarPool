package air.foi.carpool;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginRegHomescreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_reg_homescreen, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button login = (Button) getView().findViewById(R.id.LoginNavButton);
        login.setText(R.string.home_login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment lf = new LoginFragment();

                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, lf);
                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.addToBackStack("loginHome");
                fm.commit();
            }
        });

        Button register = (Button) getView().findViewById(R.id.RegisterNavButton);
        register.setText(R.string.home_reg_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment rf = new RegisterFragment();

                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, rf);
                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.addToBackStack("registerHome");
                fm.commit();
            }
        });

    }

}
