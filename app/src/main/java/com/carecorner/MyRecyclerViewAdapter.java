package com.carecorner;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

/**********************************
 * This code has been adapted from:
 * Title: RecyclerView simple adapter
 * Author: Jin Lim
 * Date: 2019
 * Availability: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 **********************************/

public class MyRecyclerViewAdapter<ItemClickListener> extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements OnItemClickListener {
    private List<String> data;
    private OnItemClickListener OnItemClickListener;
    public int index;

    public MyRecyclerViewAdapter(JournalRecyclerMain journalRecyclerMain, List<String> data){
        this.data = data;
    }
    //inflates row layout when needed from xml
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_view, parent, false);
        return new ViewHolder(rowItem);
    }
    //binds data to textview in each row
    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String item = data.get(position);
        //holder.textView.setText(this.data.get(position));
        holder.textView.setText(item);
        holder.itemView.setTag(position);

    }
    //total number of rows
    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public static int index;
        private TextView textView;
       // private Button btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.textView = itemView.findViewById(R.id.textview);
            this.index = getLayoutPosition();

         //   btnDelete = (Button) view.findViewById(R.id.btnDelete);
           // getLayoutPosition();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
            //index = getLayoutPosition();
            //if (OnItemClickListener != null) {
                //new Handler().postDelayed(new Runnable() {
                    //@Override
                   // public void run() {
                        //OnItemClickListener.onItemClick(view.getTag());
                    //}
               // }, 0);
            //}
        //}
        }
    }
    //get data at click position
    int getItem(int id) {
        return Integer.parseInt(data.get(id));
    }

    //allows click events to be caught
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.OnItemClickListener = OnItemClickListener;
    }
    //parent activity will implement this method to respond to click events
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
