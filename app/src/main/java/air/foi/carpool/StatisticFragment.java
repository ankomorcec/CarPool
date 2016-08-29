package air.foi.carpool;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import air.foi.db.Passenger;
import air.foi.db.Travel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoniok on 28.8.2016..
 * Prikaz Statističkih podataka vožnje s drugima i prijevoza drugih
 */
public class StatisticFragment extends Fragment{
    @BindView(R.id.chart) HorizontalBarChart chart;

    float [] floatHelperPsngr = {0,0};
    float [] floatHelperTrvl = {0,0};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_chart_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        List<IBarDataSet> dataSets = new ArrayList<IBarDataSet>(); // Lista data Setova
        List<BarEntry> entries1 = new ArrayList<BarEntry>();
        List<BarEntry> entries2 = new ArrayList<BarEntry>();
        getStatData();
        entries1.add(new BarEntry(floatHelperPsngr[0],floatHelperPsngr[1]));
        entries2.add(new BarEntry(floatHelperTrvl[0], floatHelperTrvl[1]));
        BarDataSet dataSet1 = new BarDataSet(entries1,"Riding Along");
        BarDataSet dataSet2 = new BarDataSet(entries2,"Driving");
        dataSet2.setColor(R.color.redChart);

        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        BarData data = new BarData(dataSets);

        chart.setData(data);
        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setValueFormatter(new MyValueFormatter());
        chart.getLegend().setEnabled(true);
        chart.getLegend().setTextSize(5);
        chart.setDescription("Usage Statistic");
        chart.invalidate();

    }

    /**
     * Nova klasa formatiranja prikazanog oblika na osi grafa
     */

    public class MyValueFormatter implements AxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return Math.round(value)+"";
        }

        @Override
        public int getDecimalDigits() {
            return 0;
        }
    }

    /**
     * Dohvat potrebnih podataka za prikaz grafa
     * Dohvat iz tablice Passanger za podatke vožnje s drugima
     * Dohvat iz tablice Travel za podatke vožnje drugih
     */

    public void getStatData(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String currentUser=settings.getString("username", null);
        String currentUserId = settings.getString("user_id", null);
        floatHelperPsngr = Passenger.getStatTravelData(currentUserId);
        floatHelperTrvl = Travel.getStatTravelData(currentUser);
    }
}
