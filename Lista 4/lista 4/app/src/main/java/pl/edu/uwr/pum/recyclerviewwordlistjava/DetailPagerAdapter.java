package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailPagerAdapter extends RecyclerView.Adapter<DetailPagerAdapter.ViewHolder> {

    private Context context;
    private List<Crime> crimes;
    Calendar date;

    public DetailPagerAdapter(Context context) {
        crimes = CrimeLab.mCrimes;
        this.context = context;
    }


    @NonNull
    @Override
    public DetailPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.activity_crime, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crime currentCrime = crimes.get(position);
//        holder.bind(currentCrime);
        holder.mTitle.setText(currentCrime.getTitle());
        holder.mDate.setText(currentCrime.getDate().toString());
        holder.mSolved.setChecked(currentCrime.getSolved());

        holder.mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                date = Calendar.getInstance();
                new DatePickerDialog( context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog( context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                                currentCrime.setDate(date.getTime());
                                holder.mDate.setText(date.getTime().toString());


                            }
                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
            }
        });

        holder.mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!currentCrime.getTitle().equals(String.valueOf(s)))
                    currentCrime.setTitle(holder.mTitle.getText().toString());
        }});

        holder.mSolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                if (v.getId() == R.id.set_solved)
                    currentCrime.setSolved(checked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText mTitle;
        private CheckBox mSolved;
        private Button mDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title_edit);
            mSolved = itemView.findViewById(R.id.set_solved);
            mDate = itemView.findViewById(R.id.date_edit_button);
        }
        }
    }
