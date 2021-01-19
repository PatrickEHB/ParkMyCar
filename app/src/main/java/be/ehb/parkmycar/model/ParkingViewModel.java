package be.ehb.parkmycar.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.ehb.parkmycar.fragments.HomeFragment;

public class ParkingViewModel extends AndroidViewModel {

    private final Application mApplication;
    private LiveData<List<Parking>> parking;
    private ParkingDatabase database;

// Was om locatie optehalen maar uiteindelijk niet gebruikt
    public Uri getLocation(String p) {
        Uri location;
        Parking found = database.getParkingDao().findById(p);
        location = Uri.parse(found.getCoordonnes_coordinaten());
        return location;
    }

    public void updateParking(Parking p) {
        ParkingDatabase.mEXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                database.getParkingDao().updateParking(p);
            }
        });
    }

    public ParkingViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
        database = ParkingDatabase.getInstance(application);
        parking = database.getParkingDao().getAllParkings();
    }

    public LiveData<List<Parking>> getParkings() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mApplication);
        boolean value = pref.getBoolean("switch_preference_increase", false);
        boolean downloaded = pref.getBoolean("isDownloaded", false);
        Log.d("debug", "" + value);

        parking = null ;
        if (downloaded == false) {

            mQueue = Volley.newRequestQueue(getApplication());

            String url = "https://opendata.brussels.be/api/records/1.0/search/?dataset=parkings&q=";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("records");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject records = jsonArray.getJSONObject(i);
                                    JSONObject fields = records.getJSONObject("fields");
                                    String beheersmaatschappij = fields.getString("proprietaire_beheersmaatschappij");
                                    String name = fields.getString("nom_naam");
                                    String location = fields.getString("coordonnes_coordinaten");
                                    String recordid = records.getString("recordid");
                                    double plaatsen = fields.getDouble("nombre_de_places_aantal_plaatsen");



                                    Parking p = new Parking("Maatschapij  " + beheersmaatschappij, "Naam  " + name,
                                            plaatsen, recordid, "Neen", location);

                                    ParkingDatabase.mEXECUTOR_SERVICE.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            database.getParkingDao().insertParking(p);
                                        }
                                    });

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("test", error.getMessage());
                }
            });
            mQueue.add(request);
            pref.edit().putBoolean("isDownloaded", true).apply();
        }

        if (value == true) {
            parking = database.getParkingDao().getAllParkingsAscending();
        } else {
            parking = database.getParkingDao().getAllParkings();
        }

        return parking;

    }

    private RequestQueue mQueue;


}
