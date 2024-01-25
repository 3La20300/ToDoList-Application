package com.example.todolist.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddNewTaskStorageAdap;
import com.example.todolist.MainActivity;
import com.example.todolist.Model.Model;
import com.example.todolist.R;
import com.example.todolist.Utils.DataBaseHelper;

import java.util.List;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
private List<Model> mlist;
private MainActivity activity;
private DataBaseHelper mydb;

    public ToDoAdapter( DataBaseHelper mydb,  MainActivity activity) {
        this.mydb = mydb;
        this.activity = activity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Model item= mlist.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mydb.updateStatus(item.getId(), 1);
                }else
                    mydb.updateStatus(item.getId(), 0);
                }
        });
    }

    private boolean toBoolean(int status) {
        return status >= 1;
    }

        public Context getContext(){
        return activity;
        }

        public void  setTasks(List<Model> mlist){
        this.mlist=mlist;
        notifyDataSetChanged();
        }
        public void deleteTask(int position){
        Model item =mlist.get(position);
        mydb.deleteTask(item.getId());
        mlist.remove(position);
   //     notifyItemRemoved();
        }

        public void editItem(int  position )
        {
            Model item =mlist.get(position);
            Bundle bundle = new Bundle();
            bundle.putInt("id", item.getId());
            bundle.putString("task", item.getTask());

            AddNewTaskStorageAdap task= new AddNewTaskStorageAdap();
            task.setArguments(bundle);
            task.show(activity.getSupportFragmentManager(), task.getTag());


        }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox= itemView.findViewById(R.id.mcheckbox);
        }
    }
}
