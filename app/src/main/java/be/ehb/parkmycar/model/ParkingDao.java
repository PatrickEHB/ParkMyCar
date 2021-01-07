package be.ehb.parkmycar.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ParkingDao {

    @Query("SELECT * FROM Parking ORDER BY proprietaire_beheersmaatschappij")
    LiveData<List<Parking>> getAllParkings();

    @Query("SELECT * FROM Parking ORDER BY nombre_de_places_aantal_plaatsen ASC")
    LiveData<List<Parking>> getAllParkingsAscending();

    @Query("SELECT * FROM Parking WHERE recordid = :id ")
    Parking findById(String id);

    @Insert
    void insertParking(Parking parking);

    @Update
    void updateParking(Parking parking);


}
