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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {
    @BindView(R.id.LoginButton) Button login;
    @BindView(R.id.username_edit) TextView userNameTxtView;
    @BindView(R.id.password_edit) TextView passTxtView;

    @OnClick(R.id.LoginButton)
    public void loginClick() {
        String username = userNameTxtView.getText().toString();
        String password = passTxtView.getText().toString();
        if(!(username.equals("") && password.equals("")) && User.validateUser(username, password)){
            Toast.makeText(getActivity(), R.string.login_success, Toast.LENGTH_SHORT).show();

            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("username", username);
            editor.commit();

            String currentUser=User.getUserId(username);
            editor.putString("user_id", currentUser);
            editor.commit();

            ChooseListingType vt = new ChooseListingType();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        login.setText(R.string.home_login_button);
    }
}
