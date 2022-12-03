package com.setbitzero.taskmaneger.helper;


import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.util.Calendar;

public class DateTimeHelper {
    @SuppressLint("SetTextI18n")
    public static void timeDialog(Context context, int key, String title, EditText editTexts){
        
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, (timePicker, selectedHour, selectedMinute) -> {

            if(key == 0) editTexts.setText(format12(selectedHour, selectedMinute));
            else if(key == 1) editTexts.setText(format12(selectedHour, selectedMinute));
        }, hour, minute, false);
        mTimePicker.setTitle(title);
        mTimePicker.show();
    }

    //convert hour 24 to 12
    @SuppressLint("DefaultLocale")
    private static String format12(int selectedHour, int selectedMinute){
        String ampm = "AM";
        if (selectedHour >= 12)
            ampm = "PM";
        selectedHour = selectedHour % 12;
        if (selectedHour == 0)
            selectedHour = 12;

        return String.format("%d : %d %s", selectedHour, selectedMinute, ampm);
    }
}
