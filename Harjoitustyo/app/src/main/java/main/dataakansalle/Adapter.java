package main.dataakansalle;

// Adapter for recycleview
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<Item> items;
    public Adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.PopulationDevelopment.setText(items.get(position).getPopulationDevelopment());
        holder.JobSelfSufficiency.setText(items.get(position).getJobSelfSufficiency());
        holder.EmploymentRate.setText(items.get(position).getEmploymentRate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}