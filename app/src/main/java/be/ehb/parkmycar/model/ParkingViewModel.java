package be.ehb.parkmycar.model;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

import be.ehb.parkmycar.fragments.HomeFragment;

public class ParkingViewModel extends AndroidViewModel {


    private MutableLiveData<ArrayList<Parking>> parking;

    public ParkingViewModel(@NonNull Application application) {
        super(application);
        if (parking == null) {
            mQueue = Volley.newRequestQueue(getApplication());
            parking = new MutableLiveData<>();
            ArrayList<Parking> testParkings = new ArrayList<>();
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
                                    String  beheersmaatschappij = fields.getString("proprietaire_beheersmaatschappij");
                                    String name = fields.getString("nom_naam");
                                    String recordid = records.getString("recordid");
                                    int plaatsen = fields.getInt("nombre_de_places_aantal_plaatsen");
                                    String places = plaatsen + " ";
                                    testParkings.add(new Parking("Maatschapij  "+beheersmaatschappij, "Naam  "+name, "Plaatsen  "+places,recordid, Uri.parse("geo:40,3?z=8")));
                                    parking.setValue(testParkings);
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

        }

    }

    public MutableLiveData<ArrayList<Parking>> getParkings() {
        return parking;
    }

    //    public MutableLiveData<ArrayList<Parking>> getParkings() {
//        if (parking == null) {
//            parking = new MutableLiveData<>();
//            ArrayList<Parking> testParkings = new ArrayList<>();
//            testParkings.add(new Parking("test1naam", "test1maatschapij","50", "1", Uri.parse("geo:40,3?z=8")));
//            testParkings.add(new Parking("test1naam", "test1maatschapij","50", "zd", Uri.parse("geo:40,3?z=8")));
//            testParkings.add(new Parking("test1naam", "test1maatschapij","50", "785", Uri.parse("geo:40,3?z=8")));
//            testParkings.add(new Parking("test1naam", "test1maatschapij","50", "7575", Uri.parse("geo:40,3?z=8")));
//            parking.setValue(testParkings);
//        }
//        return parking;
//    }
    private RequestQueue mQueue;

//    public MutableLiveData<ArrayList<Parking>> getParkings() {
//        if (parking == null) {
//            mQueue = Volley.newRequestQueue(getApplication());
//            parking = new MutableLiveData<>();
//            ArrayList<Parking> testParkings = new ArrayList<>();
//            String url = "https://opendata.brussels.be/api/records/1.0/search/?dataset=parkings&q=";
//
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                JSONArray jsonArray = response.getJSONArray("records");
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject records = jsonArray.getJSONObject(i);
//                                    String name = records.getString("nom_naam");
//                                    String plaatsen = records.getString("nombre_de_places_aantal_plaatsen");
//
//                                    testParkings.add(new Parking(name, "name", "plaatsen", plaatsen, Uri.parse("geo:40,3?z=8")));
//                                    parking.setValue(testParkings);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("test", error.getMessage());
//                }
//            });
//            mQueue.add(request);
//
//        }
//        return parking;
//    }


}
