package com.setbitzero.e_student.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.setbitzero.e_student.databinding.ItemViewBinding;
import com.setbitzero.e_student.models.StudentModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    Context context;
    ArrayList<StudentModel> studentModels;

    public CustomAdapter(Context context, ArrayList<StudentModel> studentModels) {
        this.context = context;
        this.studentModels = studentModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewBinding binding = ItemViewBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setStudent(new StudentModel(
                studentModels.get(position).getId(),
                studentModels.get(position).getName(),
                studentModels.get(position).getAge(),
                studentModels.get(position).getDepartment(),
                studentModels.get(position).getSemester()));

        holder.binding.editBtn.setOnClickListener(v-> Toast.makeText(context, "Edit "+position, Toast.LENGTH_LONG).show());
        holder.binding.deleteBtn.setOnClickListener(v-> Toast.makeText(context, "Delete "+position, Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return studentModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemViewBinding binding;

        public ViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
