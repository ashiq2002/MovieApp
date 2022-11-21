package com.setbitzero.e_student.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.setbitzero.e_student.R;
import com.setbitzero.e_student.models.StudentModel;

import java.util.Objects;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<StudentModel, FirebaseAdapter.ViewHolder> {
    Context context;

    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, final int position, @NonNull final StudentModel model) {
        holder.id.setText(model.getId());
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.department.setText(model.getDepartment());

        //holder.binding.deleteBtn.setOnClickListener(view-> Toast.makeText(view.getContext(), "Delete "+position, Toast.LENGTH_LONG).show());
        //holder.binding.editBtn.setOnClickListener(view-> Toast.makeText(view.getContext(), "Edit "+position, Toast.LENGTH_LONG).show());

        holder.editBtn.setOnClickListener(v-> updateDialog(model));
        holder.deleteBtn.setOnClickListener(v-> delete(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemViewBinding binding = ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new ViewHolder(binding);

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        //ItemViewBinding binding;
        TextView id, name, age, department;
        MaterialButton deleteBtn, editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.sIdTitle);
            name = itemView.findViewById(R.id.sNameTitle);
            age = itemView.findViewById(R.id.sAgeTitle);
            department = itemView.findViewById(R.id.sDepartmentTitle);

            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);
        }

//        public ViewHolder(@NonNull ItemViewBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }

    }

    private void updateDialog(StudentModel model){
        EditText name, age, department, semester;
        Button submitBtn;
        DialogPlus dialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_layout))
                .setExpanded(true, 800)
                .create();

//        View view = dialogPlus.getHeaderView();
//        name = view.findViewById(R.id.uName);
//        age = view.findViewById(R.id.uAge);
//        department = view.findViewById(R.id.uDepartment);
//        semester = view.findViewById(R.id.uSemester);
//
//        name.setText(model.getName());
//        age.setText(model.getAge());
//        department.setText(model.getDepartment());
//        semester.setText(model.getSemester());

        dialogPlus.show();
    }

    private void delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete data")
                .setMessage("Delete?")
                .setPositiveButton("yes", (dialog, which) -> {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("StudentDetails")
                            .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

        })

                .setNegativeButton("no", (dialog, which) -> { });
        builder.show();

    }

}
