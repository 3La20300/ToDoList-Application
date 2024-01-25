package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.Model;
import com.example.todolist.Utils.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListner {

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<Model> mlist;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview=findViewById(R.id.recyclerview);
        fab=findViewById(R.id.fab);
        myDB= new DataBaseHelper(MainActivity.this);
        mlist=new ArrayList<>();
        adapter=new ToDoAdapter(myDB,MainActivity.this);

        mlist=myDB.getAllTasks();
        //Collections.reverse(mlist);
        adapter.setTasks(mlist);

             //mRecyclerview.setHasFixedSize(true);
             //mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//trial1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setAdapter(adapter);
//endoftrial1
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddNewTaskStorageAdap.newInstance().show(getSupportFragmentManager(), AddNewTaskStorageAdap.TAG);
            }
        });

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
    mlist=myDB.getAllTasks();
       // Collections.reverse(mlist);
        adapter.setTasks(mlist);
        adapter.notifyDataSetChanged();

    }
}