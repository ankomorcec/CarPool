package air.foi.carpool;

import android.app.Fragment;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoniok on 28.8.2016..
 */
public class StatisticFragment extends Fragment{
    @BindView(R.id.chart) HorizontalBarChart chart;


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
        entries1.add(new BarEntry(2,780));
        entries2.add(new BarEntry(10, 1200));
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
}
