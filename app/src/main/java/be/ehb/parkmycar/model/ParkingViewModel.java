package be.ehb.parkmycar.model;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ParkingViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Parking>> parking;

    public ParkingViewModel() {
    }

    public MutableLiveData<ArrayList<Parking>> getParkings() {
        if (parking == null) {
            parking = new MutableLiveData<>();
            ArrayList<Parking> testParkings = new ArrayList<>();
            testParkings.add(new Parking("test1naam", "test1maatschapij", "50", Uri.parse("geo:40,3?z=8")));
            testParkings.add(new Parking("test1naam", "test1maatschapij", "50", Uri.parse("geo:40,3?z=8")));
            testParkings.add(new Parking("test1naam", "test1maatschapij", "50", Uri.parse("geo:40,3?z=8")));
            testParkings.add(new Parking("test1naam", "test1maatschapij", "50", Uri.parse("geo:40,3?z=8")));
            parking.setValue(testParkings);
        }
        return parking;
    }


}
