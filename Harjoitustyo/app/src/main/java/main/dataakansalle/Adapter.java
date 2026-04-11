package main.dataakansalle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private final List<Item> items;

    public Adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.PopulationDevelopment.setText(item.getPopulationDevelopment());
        holder.JobSelfSufficiency.setText(item.getJobSelfSufficiency());
        holder.EmploymentRate.setText(item.getEmploymentRate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
