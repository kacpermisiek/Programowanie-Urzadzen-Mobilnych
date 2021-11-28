package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CrimeListAdapter extends RecyclerView.Adapter<CrimeListAdapter.CrimeViewHolder> {

    private final CrimeLab crimeList;
    private final LayoutInflater inflater;

    public static final String EXTRA_MESSAGE = "pl.edu.uwr.pum.recyclerviewwordlistjava.MESSAGE";

    public CrimeListAdapter(Context context){
        inflater = LayoutInflater.from(context);
        crimeList = CrimeLab.get(context);
    }

    public class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView crimeText;
        private final TextView crimeImage;
        final CrimeListAdapter adapter;
        private final Context context;

        public CrimeViewHolder(@NonNull View itemView, CrimeListAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            crimeText = itemView.findViewById(R.id.crime);
            crimeImage = itemView.findViewById(R.id.solved_image);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Intent intent = new Intent(context, CrimeActivity.class);
            intent.putExtra(EXTRA_MESSAGE, position);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public CrimeListAdapter.CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.crime_list_item, parent, false);
        return new CrimeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeListAdapter.CrimeViewHolder holder, int position) {
        Crime currentCrime = crimeList.getCrime(position);
        holder.crimeText.setText(currentCrime.getTitle());
        if (currentCrime.getSolved()){
            holder.crimeImage.setVisibility(View.VISIBLE);
        }
        else{
            holder.crimeImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return crimeList.getSize();
    }
}
