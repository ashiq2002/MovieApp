package com.setbitzero.e_student.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.setbitzero.e_student.adapters.FirebaseAdapter;
import com.setbitzero.e_student.databinding.ActivityMainBinding;
import com.setbitzero.e_student.databinding.FormLayoutBinding;
import com.setbitzero.e_student.models.StudentModel;
import com.setbitzero.e_student.services.FirebaseServices;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FormLayoutBinding fBinding;
    FirebaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.addStudent.setOnClickListener(v -> showAlertDialog());
        fetchData();
    }



    private void showAlertDialog(){
        fBinding = FormLayoutBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Student Details")
                .setView(fBinding.getRoot())
                .setPositiveButton("Submit", (dialog, which) ->{

                    if(addStudent(new EditText[]{fBinding.sId, fBinding.sName, fBinding.sAge, fBinding.sDepartment, fBinding.semester})){
                        Toast.makeText(getApplicationContext(), "unsuccessful", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean addStudent(EditText[] arr){
        boolean isNull = true;

        if(!arr[0].getText().toString().trim().equals("")
                && !arr[1].getText().toString().trim().equals("")
                && !arr[2].getText().toString().trim().equals("")
                && !arr[3].getText().toString().trim().equals("")
                && !arr[4].getText().toString().trim().equals("")
        ){
            FirebaseServices.setStudentData(new StudentModel(
                    arr[0].getText().toString(),
                    arr[1].getText().toString(),
                    arr[2].getText().toString(),
                    arr[3].getText().toString(),
                    arr[4].getText().toString()
            ));
            isNull = false;
        }
        return isNull;
    }

    //Fetching data from Firebase realtime database
    private void fetchData(){
        FirebaseRecyclerOptions<StudentModel> options =
                new FirebaseRecyclerOptions.Builder<StudentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("StudentDetails"), StudentModel.class)
                        .build();

        adapter = new FirebaseAdapter(options, MainActivity.this);
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //to start activity start listening data
        adapter.startListening();
    }

    //some exception inside the onStop() method
//    @Override
//    protected void onStop() {
//        super.onStop();
//        //to stop activity stop listening data
//        adapter.stopListening();
//    }
}