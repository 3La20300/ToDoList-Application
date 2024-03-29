package com.example.todolist;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolist.Model.Model;
import com.example.todolist.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTaskStorageAdap extends BottomSheetDialogFragment {

    public static final String TAG ="AddNewTask";

private EditText mEditText;
private Button mSaveButton;
private DataBaseHelper mydb;

    public static AddNewTaskStorageAdap newInstance(){
        return new AddNewTaskStorageAdap();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.add_new, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       mEditText=view.findViewById(R.id.edittext);
       mSaveButton=view.findViewById(R.id.button_save);

       mydb=new DataBaseHelper(getActivity());
       boolean isUpdate=false;

       Bundle bundle=getArguments();
       if(bundle != null){
           isUpdate=true;
           String task= bundle.getString("task");
           mEditText.setText(task);

//           if (task.length()>0)   //try 34an m4 fhmha awy
//           {
//                mSaveButton.setEnabled(false);
//           }
       }
            mEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if(charSequence.toString().equals("")){
                        mSaveButton.setEnabled(false);
                        mSaveButton.setBackgroundColor(Color.GRAY);
                    }else{
                        mSaveButton.setEnabled(true);
                        mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

       final boolean finalIsUpdate=isUpdate;
       mSaveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String text = mEditText.getText().toString();

               if(finalIsUpdate){
                   mydb.updateTask(bundle.getInt("id"), text);

               }else {
                   Model item = new Model(0, text, 0);
                   mydb.insertTask(item);
               }
               dismiss();
           }
       });

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity= getActivity();
        if(activity instanceof OnDialogCloseListner){
            ((OnDialogCloseListner)activity).onDialogClose(dialog);
        }
    }
}
