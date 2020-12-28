package be.ehb.parkmycar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mNavController = Navigation.findNavController(this, R.id.nav_host);
        mAppBarConfiguration = new AppBarConfiguration.Builder().build();
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);

//        String jsonFileString = ReadJson.getJsonFile(getApplicationContext(), "parking.json");
//        Log.i("data",jsonFileString);
//
//        Gson gson = new Gson();
//        Type listParking = new TypeToken<List<Parking>>() {
//        }.getType();
//        List<Parking> parking = gson.fromJson(jsonFileString, listParking);
//        for (int i = 0;i<parking.size();i++){
//            Log.i("data",">Item"+i+"\n"+parking.get(i));
//        }
    }


}