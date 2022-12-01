package com.setbitzero.taskmaneger.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.setbitzero.taskmaneger.model.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(TaskModel taskModel);

    @Query("SELECT *FROM task")
    List<TaskModel> getAllData();

    @Query("SELECT *FROM task WHERE status = \"Pending\"")
    List<TaskModel> getPendingTask();

    @Query("SELECT *FROM task WHERE status = \"Running\"")
    List<TaskModel> getRunningTask();

    @Query("SELECT *FROM task WHERE status = \"Complete\"")
    List<TaskModel> getCompleteTask();

    @Query("DELETE FROM task WHERE id = :id")
    void deleteTask(int id);

    @Query("UPDATE task SET title = :title, description = :description," +
            " status = :status, start_time = :start_time, end_time = :end_time WHERE id = :id")
    void updateTask(int id, String title, String description, String status, String start_time, String end_time);
}
