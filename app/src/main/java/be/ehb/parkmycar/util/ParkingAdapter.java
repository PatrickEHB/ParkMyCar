package be.ehb.parkmycar.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.ehb.parkmycar.R;
import be.ehb.parkmycar.model.Parking;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    private ArrayList<Parking> items;


    class ParkingViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNaam, txtMaatschapij, txtplaatsen;

        public ParkingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNaam = itemView.findViewById(R.id.txt_naam_parking);
            txtMaatschapij = itemView.findViewById(R.id.txt_naam_maatschap);
            txtplaatsen = itemView.findViewById(R.id.txt_aantal_plaatsen);
        }

    }

    public ParkingAdapter() {
        this.items = new ArrayList<Parking>();
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context mContext = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View card = mLayoutInflater.inflate(R.layout.park_card, parent, false);

        return new ParkingViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        Parking currentParking = items.get(position);
        holder.txtNaam.setText(currentParking.getProprietaire_beheersmaatschappij());
        holder.txtMaatschapij.setText(currentParking.getNom_naam());
        holder.txtplaatsen.setText(currentParking.getNombre_de_places_aantal_plaatsen());
    }

    public void addParking(ArrayList<Parking> newParkings) {
        items = newParkings;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
