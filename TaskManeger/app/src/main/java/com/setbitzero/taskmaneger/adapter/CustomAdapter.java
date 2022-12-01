package com.setbitzero.taskmaneger.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.setbitzero.taskmaneger.R;
import com.setbitzero.taskmaneger.database.DatabaseHelper;
import com.setbitzero.taskmaneger.databinding.ItemBinding;
import com.setbitzero.taskmaneger.fragments.UpdateTaskFragment;
import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.ArrayList;
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
                    updateData(position);
                    return true;

                case R.id.delete:
                    //to delete from database
                    databaseHelper.getTaskDao().deleteTask(list.get(position).getId());
                    //to remove from list
                    list.remove(position);
                    notifyDataSetChanged();
                    return true;

                case R.id.complete:
                    Toast.makeText(context, "complete"+position, Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    private void updateData(int position) {
        Navigation.findNavController((Activity) context, R.id.nav_controller).navigate(R.id.updateTaskFragment);
        ((Activity) context).findViewById(R.id.topBar).setVisibility(View.GONE);
        ((Activity) context).findViewById(R.id.navigationBar).setVisibility(View.GONE);
    }

}
