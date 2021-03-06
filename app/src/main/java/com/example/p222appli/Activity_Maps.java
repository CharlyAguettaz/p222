package com.example.p222appli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.location.LocationManager.GPS_PROVIDER;


public class Activity_Maps extends AppCompatActivity implements LocationListener, OnMapReadyCallback {

    private static final int PERMS_CALL_ID = 1234;
    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private SearchView searchView;
    private double lat;
    private double lon;
    private LatLng latLng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__maps);




        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.maps);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        searchView = findViewById(R.id.sv_location);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {



            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder (Activity_Maps.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(location);
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                }

                return false;
            }



            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    public List<Lieu> readCsvFile() {
        List<Lieu> listLieu = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.trier);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("windows-1252"))
        );

        String line ;

        try {

            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",,,,,");
                Lieu lieu = new Lieu();
                lieu.setAdresse(tokens[0]);
                lieu.setType(Integer.valueOf((tokens[1])));
                listLieu.add(lieu);
                System.out.println(lieu.getType());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLieu;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();


    }
    protected void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,}, PERMS_CALL_ID );
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if ( lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            lm.requestLocationUpdates( LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if ( lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates( LocationManager.GPS_PROVIDER, 10000, 0, this);
        }

        lat = lm.getLastKnownLocation(GPS_PROVIDER).getLatitude();
        lon = lm.getLastKnownLocation(GPS_PROVIDER).getLongitude();
        loadMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ( requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (lm != null){
            lm.removeUpdates(this);
        }
    }
    @SuppressWarnings( "MissingPermission")
    private void loadMap(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Activity_Maps.this.googleMap = googleMap;
                latLng2 = new LatLng(lat, lon);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((latLng2), 15));
                googleMap.setMyLocationEnabled( true );
                List<Address> listAdresse2 = null;
                List<Lieu> listLieu = readCsvFile();


                for (int i = 0; i < listLieu.size(); i++) {

                    String lieu = listLieu.get(i).getAdresse();
                    Geocoder geocoder = new Geocoder(Activity_Maps.this);

                    try {
                        listAdresse2 = (geocoder.getFromLocationName(lieu, 1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    LatLng latLng = new LatLng(listAdresse2.get(0).getLatitude(), listAdresse2.get(0).getLongitude());
                    if (distanceBetween(latLng.latitude, latLng.longitude, latLng2.latitude, latLng2.longitude) < 20) {
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(lieu).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    }


                }
            }
        });
    }
    public static double distanceBetween(double lat1, double lon1, double lat2, double lon2){
        if((lat1 == lat2) && (lon1 == lon2)){
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist *= 1.609344;
            return dist;
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Toast.makeText(this, "Location: " + latitude + "/" + longitude, Toast.LENGTH_LONG).show();
        if (googleMap != null) {
            LatLng googleLocation = new LatLng ( latitude, longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));
        }
    }

    private Menu m = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        m = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId() ){
            case R.id.item1:
                Intent myIntent = new Intent(this.getApplicationContext(), Activity_Welcome.class);
                startActivityForResult(myIntent, 0);
                return true;
            case R.id.item3:
                Intent myIntent2 = new Intent(this.getApplicationContext(), Activity_WasteSorting.class);
                startActivityForResult(myIntent2, 1);
                return true;
            case R.id.item4:
                Intent myIntent3 = new Intent(this.getApplicationContext(), Activity_Maps.class);
                startActivityForResult(myIntent3, 2);
                Toast.makeText(Activity_Maps.this, R.string.goingToMap, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}