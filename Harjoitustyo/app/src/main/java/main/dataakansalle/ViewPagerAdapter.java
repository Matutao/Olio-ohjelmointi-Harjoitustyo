package main.dataakansalle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import main.dataakansalle.fragments.PopulationFragment;
import main.dataakansalle.fragments.WeatherFragment;
import main.dataakansalle.fragments.VehiclesFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PopulationFragment();
            case 1:
                return new WeatherFragment();
            case 2:
                return new VehiclesFragment();
            default:
                return new PopulationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

