package air.foi.carpool;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        ActiveAndroid.initialize(getApplication());
        setContentView(R.layout.activity_main);

        LoginRegHomescreen lrhf = new LoginRegHomescreen();
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, lrhf);
        fm.commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
