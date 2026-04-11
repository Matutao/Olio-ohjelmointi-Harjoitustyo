package main.dataakansalle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import main.dataakansalle.fragments.PopulationFragment;
import main.dataakansalle.fragments.WeatherFragment;
import main.dataakansalle.fragments.VehiclesFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final MunicipalityData municipalityData;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, MunicipalityData data) {
        super(fragmentActivity);
        this.municipalityData = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return PopulationFragment.newInstance(municipalityData);
            case 1:
                return WeatherFragment.newInstance(municipalityData);
            case 2:
                return VehiclesFragment.newInstance(municipalityData);
            default:
                return PopulationFragment.newInstance(municipalityData);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
