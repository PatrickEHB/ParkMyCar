package be.ehb.parkmycar.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Parking.class}, exportSchema = false)


public abstract class ParkingDatabase extends RoomDatabase {
    private static final String DB_Project = "parkingDatabase.db";
    private static ParkingDatabase instance;

    static final ExecutorService mEXECUTOR_SERVICE =
            Executors.newFixedThreadPool(4);

    static final ParkingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ParkingDatabase.class, DB_Project).build();
        }
        return instance;
    }

    public abstract ParkingDao getParkingDao();
}
