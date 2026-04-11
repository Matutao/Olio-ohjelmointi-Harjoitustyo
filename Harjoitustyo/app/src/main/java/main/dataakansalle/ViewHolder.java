package main.dataakansalle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView PopulationDevelopment;
    TextView JobSelfSufficiency;
    TextView EmploymentRate;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        PopulationDevelopment = itemView.findViewById(R.id.PopulationDevelopment);
        JobSelfSufficiency = itemView.findViewById(R.id.JobSelfSufficiency);
        EmploymentRate = itemView.findViewById(R.id.EmploymentRate);
    }
}
