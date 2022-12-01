package com.setbitzero.taskmaneger.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.setbitzero.taskmaneger.interfaces.TaskDao;
import com.setbitzero.taskmaneger.model.TaskModel;

@Database(entities = {TaskModel.class}, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context){
        return instance == null? instance =
                Room.databaseBuilder(context, DatabaseHelper.class, "task_db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build():
                instance;
    }

    public abstract TaskDao getTaskDao();
}
