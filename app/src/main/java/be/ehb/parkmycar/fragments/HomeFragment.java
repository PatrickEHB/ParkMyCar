package be.ehb.parkmycar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import be.ehb.parkmycar.R;
import be.ehb.parkmycar.model.Parking;
import be.ehb.parkmycar.model.ParkingViewModel;
import be.ehb.parkmycar.util.ParkingAdapter;


public class HomeFragment extends Fragment {


    ParkingAdapter parkingAdapter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {  return new HomeFragment();  }

    @Override
    public void onCreate(Bundle savedInstanceState) {   super.onCreate(savedInstanceState);  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView rvParkings = rootView.findViewById(R.id.rc_parkings);
        parkingAdapter = new ParkingAdapter();
        rvParkings.setAdapter(parkingAdapter);

        ParkingViewModel parkingModel = new ViewModelProvider(this).get(ParkingViewModel.class);
        parkingModel.getParkings().observeForever(new Observer<ArrayList<Parking>>() {
            @Override
            public void onChanged(ArrayList<Parking> parkings) {
                parkingAdapter.addParking(parkings);
            }
        });


//        parkingModel = new ViewModelProvider(getActivity()).get(ParkingViewModel.class);
//
//        RecyclerView rvParkings = rootView.findViewById(R.id.rc_parkings);
//        parkingAdapter = new ParkingAdapter();
//        rvParkings.setAdapter(parkingAdapter);
//
//        parkingModel.getParking().observe(new Observer<ArrayList<Parking>>(){
//            @Override
//            public void onChanged(ArrayList<Parking> parkings) {
//                parkingAdapter.addParking(parkings);
//            }
//        });
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//        rvParkings.setLayoutManager(manager);
        return rootView;
    }
}