package edu.lewisu.cs.peterschilder.chartlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(118709, 0));
        entries.add(new BarEntry(78600, 1));
        entries.add(new BarEntry(59000, 2));

        BarDataSet dataset = new BarDataSet(entries,"Average Salary");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Data Scientist");
        labels.add("Business Analyst");
        labels.add("Biologist");

        BarChart chart = new BarChart(getApplicationContext());
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Average Salaries");
        chart.animateY(5000);

        setContentView(chart);


    }
}
