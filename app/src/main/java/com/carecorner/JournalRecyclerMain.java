package com.carecorner;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/**********************************
 * This code has been adapted from:
 * Title: RecyclerView simple adapter
 * Author: Jin Lim
 * Date: 2019
 * Availability: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 **********************************/

public class JournalRecyclerMain extends AppCompatActivity implements MyRecyclerViewAdapter.OnItemClickListener {

    List<String> data;
    MyRecyclerViewAdapter adapter;
    private Button btnDelete, btnEdit, btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        initViews();
        //data to populate recyclerview with
        data = new ArrayList<>();
       data.add("Horse");
       data.add("Cow");

        //set up recyclerview
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyRecyclerViewAdapter(this, data);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // removeSingleItem(data.);
               // int position = (int) v.getTag();
                int position = MyRecyclerViewAdapter.ViewHolder.index;
                removeSingleItem(position);

                Toast.makeText(JournalRecyclerMain.this, "Your entry has been deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // super(v);
               // v.get
                //Intent intent = new Intent(JournalRecyclerMain.this, JournalEditorActivity.class);
               // updateSingleItem(updateIndex);
                //updateSingleItem(position);
                int position = MyRecyclerViewAdapter.ViewHolder.index;
                //int position = (int) v.getTag();
                updateSingleItem(position);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { insertSingleItem(); }
        });
    }
    private void removeSingleItem(int position) {
    //private void removeSingleItem() {
         adapter.getItem(position);
         int removeIndex = position;
       // int removeIndex = 1;
        data.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }
   // public void onItemClick(View v) {
        //int position = getLayoutPosition();
     //   int position = (int) v.getTag();
     //   Toast.makeText(this, "You clicked" + adapter.getItem(position)+"on row number"+position, Toast.LENGTH_SHORT).show();
  //  }
    public void onItemClick(View v) {
        insertSingleItem();
    }
    private void insertSingleItem() {
        //go here from journal editor screen for new item
        String item = "";
        int insertIndex = 0;
        data.add(insertIndex, item);
        //String item = "Pizza";
        //int insertIndex = 0;
        //data.add(insertIndex, item);
        adapter.notifyItemInserted(insertIndex);
        updateSingleItem(insertIndex);
    }
    private void updateSingleItem(int position) {
   // private void updateSingleItem() {
        //journal editor goes here for edited posts
        Bundle extras = getIntent().getExtras();
        String newValue = "I like pizza";
       // Intent intent = new Intent(JournalRecyclerMain.this, JournalEditorActivity.class);
       // intent.putExtra("text", adapter.getItem(position));
       // startActivity(intent);
       // String newValue;
       // newValue= extras.getString("text");
      //  int updateIndex = position;
        int updateIndex = 0;
        data.set(updateIndex, newValue);
        adapter.notifyItemChanged(updateIndex);
    }

    private void initViews() {
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnCreate = findViewById(R.id.btnCreate);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
