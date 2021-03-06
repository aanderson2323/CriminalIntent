package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Aaron on 12/31/2015.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";

    private static final String ARG_DATE = "date";
    private static final String ARG_PICKER_TYPE = "pickerType";
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public static DatePickerFragment newInstance(Date date, String pickerType){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putSerializable(ARG_PICKER_TYPE, pickerType);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
     public Dialog onCreateDialog(Bundle savedInstanceState){

        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        String pickerType = (String) getArguments().getSerializable(ARG_PICKER_TYPE);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View v;
        if (pickerType == "date") {

            v = LayoutInflater.from(getActivity())
                    .inflate(R.layout.dialog_date, null);
            mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
            mDatePicker.init(year, month, day, null);
            return new AlertDialog.Builder(getActivity())
                    .setView(v)
                    .setTitle(R.string.date_picker_title)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int year = mDatePicker.getYear();
                                    int month = mDatePicker.getMonth();
                                    int day = mDatePicker.getDayOfMonth();
                                    Date date = new GregorianCalendar(year, month, day).getTime();
                                    sendResult(Activity.RESULT_OK, date);
                                }
                            })
                    .create();


        }else{
            v = LayoutInflater.from(getActivity())
                    .inflate(R.layout.dialog_time, null);
            mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);
            return new AlertDialog.Builder(getActivity())
                    .setView(v)
                    .setTitle(R.string.time_picker_title)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int hour = mTimePicker.getCurrentHour();
                                    int minute = mTimePicker.getCurrentMinute();
                                    int year = calendar.get(Calendar.YEAR);
                                    int month = calendar.get(Calendar.MONTH);
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                                    Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
                                    sendResult(Activity.RESULT_OK, date);
                                }
                            })
                    .create();

        }

    }

    private void sendResult(int resultCode, Date date){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }



}
