package be.ehb.parkmycar.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import be.ehb.parkmycar.R;
import be.ehb.parkmycar.model.Parking;
import be.ehb.parkmycar.model.ParkingViewModel;
import be.ehb.parkmycar.util.ParkingAdapter;


public class HomeFragment extends Fragment {
    private RecyclerView rvParkings;
    private RequestQueue mQueue;
    private Context mContext;
    private ParkingAdapter parkingAdapter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment frag = new HomeFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        //return new HomeFragment();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController mNavController = Navigation.findNavController(getActivity(), R.id.nav_host);
        //mNavController.navigate(R.id.go_to_about_me);
        switch (item.getItemId()) {
            case R.id.mi_about_me:
                mNavController.navigate(R.id.go_to_about_me);
                break;
            case R.id.mi_prefference_frag:
                NavigationUI.onNavDestinationSelected(item,mNavController);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        rvParkings = rootView.findViewById(R.id.rv_parkings);
        parkingAdapter = new ParkingAdapter(getActivity());
        rvParkings.setAdapter(parkingAdapter);


        ParkingViewModel parkingModel = new ViewModelProvider(getActivity()).get(ParkingViewModel.class);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvParkings.setLayoutManager(manager);

        parkingModel.getParkings().observe(getViewLifecycleOwner(),new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {

                parkingAdapter.addParking(parkings);
                parkingAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
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

    }
}