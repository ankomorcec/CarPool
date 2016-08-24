package air.foi.carpool;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import air.foi.db.Travel;

/**
 * Created by Kiwi on 22.8.2016..
 */
public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.gmap_layout, container, false);

        Button addTravel = (Button) rootView.findViewById(R.id.AddTravelButtonMap);
        addTravel.setText(R.string.add_travel);
        addTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTravel vt = new AddNewTravel();
                FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, vt);
                fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.addToBackStack("viewTravelsFragment");
                fm.commit();
            }
        });

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                List<Travel> allTravels=Travel.getAll();
                for (Travel trv:allTravels) {

                    LatLng location = new LatLng(trv.getStart_lat(), trv.getStart_long());
                    Marker m = googleMap.addMarker(new MarkerOptions().position(location).title(trv.getStartPoint()).snippet("Creator: "+trv.getUser()));
                    m.setTag(trv.getId().toString());
                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                        @Override
                        public void onInfoWindowClick(Marker arg0) {
                            String recId = arg0.getTag().toString();

                            Bundle args = new Bundle();
                            args.putLong("id", (Long) Long.parseLong(recId));

                            TravelDetailsFragment tdf = new TravelDetailsFragment();
                            tdf.setArguments(args);

                            FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
                            fm.replace(R.id.fragment_container, tdf);
                            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fm.addToBackStack("viewTravelsFragment");
                            fm.commit();
                        }
                    });
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                // For zooming automatically to the location of the marker

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}