package be.ehb.parkmycar.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.parkmycar.R;
import be.ehb.parkmycar.model.Parking;
import be.ehb.parkmycar.model.ParkingViewModel;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    private List<Parking> items;

    private FragmentActivity activity;

    public ParkingAdapter(FragmentActivity activity) {
        this.items = new ArrayList<>();
        this.activity = activity;
    }

    class ParkingViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNaam, txtMaatschapij, txtplaatsen;
        final Button btnToLocation;
        final CheckBox fav;



        public ParkingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNaam = itemView.findViewById(R.id.txt_naam_parking);
            txtMaatschapij = itemView.findViewById(R.id.txt_naam_maatschap);
            fav = itemView.findViewById(R.id.check_fav);
            fav.setOnClickListener(favorite);
            txtplaatsen = itemView.findViewById(R.id.txt_aantal_plaatsen);
            btnToLocation = itemView.findViewById(R.id.btn_go_location);
            btnToLocation.setOnClickListener(toLocation);


        }

        private View.OnClickListener favorite = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Parking clicked = items.get(pos);
                if ((clicked.getFavorite()).matches("Neen")) {
                    clicked.setFavorite("Ja");
                } else {
                    clicked.setFavorite("Neen");
                }
                ParkingViewModel pmodel = new ViewModelProvider(activity).get(ParkingViewModel.class);
                pmodel.updateParking(clicked);
                notifyDataSetChanged();
            }
        };

        private View.OnClickListener toLocation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Parking found = items.get(pos);
                //           String recordid= found.getRecordid();
                //            Log.d("debug", "" + pos+"  "+recordid);
                //            ParkingViewModel pmodel = new ViewModelProvider(activity).get(ParkingViewModel.class);
                //              Uri locatie = pmodel.getLocation(recordid);
                Uri locatie = Uri.parse(found.getCoordonnes_coordinaten());
                Log.d("debug", "" + locatie);
                Intent locIntent = new Intent(Intent.ACTION_VIEW);
                String locatieClean = locatie.toString().replace("[", "");
                locatieClean = locatieClean.replace("]", "");
                Uri data = Uri.parse("geo:" + locatieClean + "?q=" + locatieClean + "(Label+Name)");
                locIntent.setData(data);
                Log.d("debug", "" + data);
                activity.startActivity(locIntent);
            }
        };

    }


    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context mContext = parent.getContext();
       // LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View card = LayoutInflater.from(mContext).inflate(R.layout.park_card, parent, false);

        return new ParkingViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        Parking currentParking = items.get(position);
        holder.txtNaam.setText(currentParking.getProprietaire_beheersmaatschappij());
        holder.txtMaatschapij.setText(currentParking.getNom_naam());
        String parkings = String.valueOf(currentParking.getNombre_de_places_aantal_plaatsen());
        parkings = "Plaatsen: " + parkings.substring(0, parkings.length() - 2);
        holder.txtplaatsen.setText(parkings);
        if ((currentParking.getFavorite()).matches("Neen")) {
            holder.fav.setChecked(false);
        } else {
            holder.fav.setChecked(true);
        }
        holder.fav.setText(currentParking.getFavorite());
    }

    public void addParking(List<Parking> newParkings) {
        items.clear();
        items = newParkings;


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
