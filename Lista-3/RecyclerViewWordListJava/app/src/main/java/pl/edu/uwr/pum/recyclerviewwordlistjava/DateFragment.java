package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;


public class DateFragment extends Fragment {

    public static final String DIALOG_DATE = "DATE";
    public static final String REQUEST_KEY = "request";

    private TextView textView;
    private Button button;
    private Date date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        textView = view.findViewById(R.id.dateTextView);
        button = view.findViewById(R.id.dateButton);

        date = new Date();

        textView.setText(date.toString());



        button.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            DatePickerDialog dialog = DatePickerDialog.newInstance(date);
            dialog.show(fragmentManager, DIALOG_DATE);
        });

        getActivity().getSupportFragmentManager().setFragmentResultListener(
                REQUEST_KEY,
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Date returnDate = (Date) result.getSerializable(DatePickerDialog.RETURN_KEY);
                        textView.setText(returnDate.toString());
                    }
                });
        return view;
        }
    }
