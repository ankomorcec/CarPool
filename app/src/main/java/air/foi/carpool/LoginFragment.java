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
import android.widget.Toast;

import air.foi.db.User;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button login = (Button) getView().findViewById(R.id.LoginButton);
        login.setText(R.string.home_login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((TextView) getActivity().findViewById(R.id.username_edit)).getText().toString();
                String password = ((TextView) getActivity().findViewById(R.id.password_edit)).getText().toString();
                if(!(username.equals("") && password.equals("")) && User.validateUser(username, password)){
                    Toast.makeText(getActivity(), R.string.login_success, Toast.LENGTH_SHORT).show();

                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", username);
                    editor.commit();

                    String currentUser=User.getUserId(username);
                    editor.putString("user_id", currentUser);
                    editor.commit();

                    choose_listing_type vt = new choose_listing_type();
                    FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                    fm.replace(R.id.fragment_container, vt);
                    fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fm.addToBackStack("registerFragment");
                    fm.commit();
                    }
                else{
                    Toast.makeText(getActivity(), R.string.login_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
