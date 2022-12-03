package com.setbitzero.taskmaneger.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.database.DatabaseHelper;
import com.setbitzero.taskmaneger.databinding.ItemBinding;
import com.setbitzero.taskmaneger.helper.DateTimeHelper;
import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    DatabaseHelper databaseHelper;
    Context context;
    List<TaskModel> list;

    public CustomAdapter(Context context, List<TaskModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.itemTitle.setText(list.get(position).getTitle());
        holder.binding.itemDescription.setText(list.get(position).getDescription());
        holder.binding.startTime.setText(String.format("Start : %s", list.get(position).getStartTime()));
        holder.binding.endTime.setText(String.format("End : %s", list.get(position).getEndTime()));

        if(list.get(position).getStatus().equalsIgnoreCase("pending"))
            holder.binding.taskIcon.setImageResource(R.drawable.ic_pending);
        else if(list.get(position).getStatus().equalsIgnoreCase("running"))
            holder.binding.taskIcon.setImageResource(R.drawable.ic_running);
        else if(list.get(position).getStatus().equalsIgnoreCase("complete"))
            holder.binding.taskIcon.setImageResource(R.drawable.ic_complete);

        holder.binding.itemAction.setOnClickListener(v-> showPopupMenu(holder, position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemBinding binding;

        public ViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    private void showPopupMenu(@NonNull ViewHolder holder, int position){
        databaseHelper = DatabaseHelper.getInstance(context);

        PopupMenu popupMenu = new PopupMenu(context, holder.binding.getRoot());
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()){
                case R.id.update:
                    update(position);
                    return true;

                case R.id.delete:
                    //to delete from database
                    databaseHelper.getTaskDao().deleteTask(list.get(position).getId());
                    //to remove from list
                    list.remove(position);
                    notifyDataSetChanged();
                    return true;

                case R.id.complete:
                    databaseHelper.getTaskDao().completeTask(list.get(position).getId());
                    list.remove(position);
                    notifyItemRemoved(position);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void update(int position){
        TextInputEditText title, description, status;
        EditText startTime, endTime;
        MaterialButton updateButton;

        DialogPlus dialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_layout))
                .setExpanded(true, 770)
                .create();

        View view = dialogPlus.getHolderView().getRootView();
        title = view.findViewById(R.id.titleId);
        description = view.findViewById(R.id.descriptionId);
        status = view.findViewById(R.id.statusId);
        startTime = view.findViewById(R.id.uStartTime);
        endTime = view.findViewById(R.id.uEndTime);
        updateButton = view.findViewById(R.id.updateData);

        //set text
        title.setText(list.get(position).getTitle());
        description.setText(list.get(position).getDescription());
        status.setText(list.get(position).getStatus());
        startTime.setText(list.get(position).getStartTime());
        endTime.setText(list.get(position).getEndTime());

        dialogPlus.show();

        //show time dialog
        startTime.setOnClickListener(v->DateTimeHelper.timeDialog(view.getContext(), 0, "Select Start Time", startTime));
        endTime.setOnClickListener(v->DateTimeHelper.timeDialog(view.getContext(), 1, "Select End Time", endTime));
        updateButton.setOnClickListener(v->{ DatabaseHelper.getInstance(view.getContext())
                .getTaskDao()
                .updateTask(list.get(position).getId(),
                        title.getText().toString(),
                        description.getText().toString(),
                        status.getText().toString(),
                        startTime.getText().toString(),
                        endTime.getText().toString());
            dialogPlus.dismiss();
        });
        notifyDataSetChanged();

    }

}
