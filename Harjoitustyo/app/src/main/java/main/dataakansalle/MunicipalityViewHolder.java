package main.dataakansalle;

// viewholder for last searched cities

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MunicipalityViewHolder extends RecyclerView.ViewHolder {
    TextView LastSearched;

    public MunicipalityViewHolder(@NonNull View itemView) {
        super(itemView);
        LastSearched = itemView.findViewById(R.id.LastSearched);
    }
}

