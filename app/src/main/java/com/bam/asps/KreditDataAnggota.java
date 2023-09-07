package com.bam.asps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class KreditDataAnggota extends AppCompatActivity {

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kredit_anggota);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        String NA = intent.getStringExtra("Nama_Anggota");

        pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> records = new ArrayList<>();
        records.add(new PieEntry(32,"Quarter 1"));
        records.add(new PieEntry(14,"Quarter 2"));
        records.add(new PieEntry(34,"Quarter 3"));
        records.add(new PieEntry(38,"Quarter 4"));

        PieDataSet dataSet = new PieDataSet(records, NA);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText(NA);
        pieChart.animate();
    }
}