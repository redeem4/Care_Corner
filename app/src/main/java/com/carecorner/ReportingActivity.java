package com.carecorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportingActivity extends AppCompatActivity implements ReportingAdapter.ItemClickListener, AddReportingDialog.AddReportingDialogListener {

    ArrayList<reporting> data;
    ReportingAdapter adapter;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporting_activity);
        initViews();
        setupRecyclerView();
    }

    @Override
    public void onItemClick(View view, int position) {
        saveArrayList(data, "reporting");
        Intent intent = new Intent (ReportingActivity.this, ReportingReader.class);
        intent.putExtra("reportingName", adapter.getItem(position).getName());
        startActivity(intent);
    }

    @Override
    public void onDeleteBtnClick(int position) {
        data.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void openDialog() {
        AddReportingDialog addReportingEntry = new AddReportingDialog();
        addReportingEntry.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String reportingName) {
        int insertIndex = data.size();
        if(reportingName.equals(""))
        {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
            reportingName = formatter.format(date);
        }
        if (reportingName.length() >= 14)
        {
            reportingName = reportingName.substring(0,13) + "...";
        }
        reporting temp = new reporting(reportingName);
        data.add(insertIndex, temp);
        adapter.notifyItemInserted(insertIndex);
    }

    private void setupRecyclerView() {
        data = getArrayList("reporting");
        if(data.isEmpty())
        {
            data.add(new reporting("test 4/1/2021"));
            data.add(new reporting("test 4/2/2021"));
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new ReportingAdapter(this, data, this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(ReportingActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void saveArrayList(ArrayList<reporting> data, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(key, json);
        editor.apply();
    }


    public ArrayList<reporting> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Journal>>() {}.getType();
        return gson.fromJson(json, type);
    }
}