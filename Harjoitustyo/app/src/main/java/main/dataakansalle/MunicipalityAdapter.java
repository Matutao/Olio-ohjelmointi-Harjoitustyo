package main.dataakansalle;

// Adapter class for last searched cities
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MunicipalityAdapter extends RecyclerView.Adapter<MunicipalityViewHolder> {

    Context context;
    List<MunicipalityItem> municipalities;
    public MunicipalityAdapter(Context context, List<MunicipalityItem> municipalities) {
        this.context = context;
        this.municipalities = municipalities;
    }

    @NonNull
    @Override
    public MunicipalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MunicipalityViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MunicipalityViewHolder holder, int position) {
        holder.LastSearched.setText(municipalities.get(position).getSearchedMunicipality());
    }

    @Override
    public int getItemCount() {
        return municipalities.size();
    }
}