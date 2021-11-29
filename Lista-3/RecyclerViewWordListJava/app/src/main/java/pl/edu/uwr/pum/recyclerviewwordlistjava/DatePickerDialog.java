package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerDialog extends DialogFragment {

    private static final String ARG_DATE = "date";
    public static final String RETURN_KEY = "return";

    private DatePicker datePicker;

    public static DatePickerDialog newInstance (Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerDialog fragment = new DatePickerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.date_picker_dialog, null);

        datePicker = view.findViewById(R.id.datePicker);
        datePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Chosen Date")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();

                        Date returnDate = new GregorianCalendar(year, month, day).getTime();

                        CrimeActivity.currentCrime.setDate(returnDate);

                        Bundle returnBundle = new Bundle();
                        returnBundle.putSerializable(RETURN_KEY, returnDate);
                        requireActivity().getSupportFragmentManager().setFragmentResult(
                                DateFragment.REQUEST_KEY,
                                returnBundle);
                    }
                })
                .create();
    }
}
