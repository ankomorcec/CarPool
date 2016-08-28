package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
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


public class RegisterFragment extends Fragment {
    @BindView(R.id.RegisterButton) Button login;
    @BindView(R.id.username_edit) TextView userNameTxtView;
    @BindView(R.id.password_edit) TextView passTxtView;

    @OnClick(R.id.RegisterButton)
    public void loginClick() {
        String username = userNameTxtView.getText().toString();
        String password = passTxtView.getText().toString();
        if(!(username.equals("") && password.equals(""))){
            User user = new User(username, password);
            user.save();

            Toast.makeText(getActivity(), R.string.register_success, Toast.LENGTH_SHORT).show();

            LoginFragment lf = new LoginFragment();
            FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, lf);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("registerFragment");
            fm.commit();
        }
        else{
            Toast.makeText(getActivity(), R.string.register_fail, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        login.setText(R.string.home_reg_button);
    }
}
