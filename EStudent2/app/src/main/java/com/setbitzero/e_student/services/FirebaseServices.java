package com.setbitzero.e_student.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.setbitzero.e_student.models.StudentModel;



public class FirebaseServices {

    public static DatabaseReference getNode(){
        return FirebaseDatabase.getInstance().getReference("StudentDetails");
    }

    public static void setStudentData(StudentModel model){
        getNode().child(model.getId()).setValue(model);
    }

}
