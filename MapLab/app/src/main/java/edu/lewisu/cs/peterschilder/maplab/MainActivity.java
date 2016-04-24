package edu.lewisu.cs.peterschilder.maplab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    boolean map_ready=false;
    GoogleMap g_map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnMap = (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map_ready){
                    g_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        Button btnSatelite = (Button)findViewById(R.id.btnSatellite);
        btnSatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map_ready){
                    g_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        Button btnHybrid = (Button)findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map_ready){
                    g_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map_ready = true;

        LatLng chicago = new LatLng(41.881832, -87.623177);
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(chicago);
        builder.zoom(14);
        CameraPosition target = builder.build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(41.8789, -87.6359));
        options.title("Willis Tower");
        googleMap.addMarker(options);

        g_map = googleMap;
    }
}
