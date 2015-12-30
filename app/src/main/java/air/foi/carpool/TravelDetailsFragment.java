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

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;

import air.foi.db.Travel;

/**
 * Created by ankomorcec on 30.12.2015..
 */
public class TravelDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.travel_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // get Bundle and extra data sent from caller activity
        Bundle data = getArguments();
        long id = data.getLong("id");

        Travel t = new Select().from(Travel.class).where("Id == ?", id).executeSingle();


        TextView name = ((TextView) getView().findViewById(R.id.travel_details_name));
        name.setText(t.getStartPoint() + " - " + t.getEndPoint());

        TextView description = ((TextView) getView().findViewById(R.id.travel_details_description));
        description.setText(t.getStartTime());

        /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        TextView startDate = ((TextView) getView().findViewById(R.id.travek_details_start));
        startDate.setText(sdf.format(t.getStartDate()));

        TextView endDate = ((TextView) getView().findViewById(R.id.travel_details_end));
        endDate.setText(" - " + sdf.format(t.getEndDate()));
        */

    }
}
