package be.ehb.parkmycar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import be.ehb.parkmycar.R;
import be.ehb.parkmycar.model.Parking;


public class DetailsFragment extends Fragment {

    private TextView txtNaam, txtParkings;
    private Parking chosen;

    public DetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        chosen = (getArguments() != null) ? (Parking) getArguments().getSerializable("info") : null;
        txtNaam = rootView.findViewById(R.id.txt_naam);
        txtParkings = rootView.findViewById(R.id.txt_parkings);
        if (chosen != null) {
            txtNaam.setText(chosen.getNom_naam());
            String parkings = String.valueOf(chosen.getNombre_de_places_aantal_plaatsen());
            parkings = "Plaatsen: " + parkings.substring(0, parkings.length() - 2);
            txtParkings.setText(parkings);
        }


        return rootView;
    }
}