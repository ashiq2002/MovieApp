package com.setbitzero.taskmaneger.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.setbitzero.taskmaneger.model.TaskModel;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(TaskModel taskModel);
}
