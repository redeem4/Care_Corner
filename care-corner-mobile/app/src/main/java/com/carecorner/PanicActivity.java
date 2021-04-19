package com.carecorner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.carecorner.PlatformPositioningProvider;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapView;

//HERE SDK
import com.carecorner.PermissionsRequestor.ResultListener;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
public class PanicActivity extends AppCompatActivity {

    private static final String LOG_TAG = "some stuff";
    private ConstraintLayout mapSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageView swipe_btn;
    private ImageView my_location;

    //Map
    private static final String TAG = PanicActivity.class.getSimpleName();
    private PermissionsRequestor permissionsRequestor;
    private MapView mapView;
    MapImage userIcon;
    PlatformPositioningProvider platformPositioningProvider;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        mapSheet = findViewById(R.id.map_sheet);
        swipe_btn = findViewById(R.id.map_swipe_up);
        my_location = findViewById(R.id.my_location_icon);

        //controls how the BottomSheet (map) UI works
        bottomSheetBehavior = BottomSheetBehavior.from(mapSheet);
        bottomSheetBehavior.setPeekHeight(150);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    case BottomSheetBehavior.STATE_COLLAPSED:
                        swipe_btn.setImageDrawable(getResources().getDrawable(R.drawable.swipe_up_icon));
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        swipe_btn.setImageDrawable(getResources().getDrawable(R.drawable.swipe_down_icon));
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        // Get a MapView instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);



        mapView.setOnReadyListener(new MapView.OnReadyListener() {
            @Override
            public void onMapViewReady() {
                // This will be called each time after this activity is resumed.
                // It will not be called before the first map scene was loaded.
                // Any code that requires map data may not work as expected beforehand.
                Log.d(TAG, "HERE Rendering Engine attached.");
            }
        });

        userIcon =  MapImageFactory.fromResource(this.getResources(),R.drawable.map_icon_user);
        MapMarker userMarker = new MapMarker(new GeoCoordinates(39.17719, -77.21111), userIcon);
        mapView.getMapScene().addMapMarker(userMarker);

        //user location
        platformPositioningProvider = new PlatformPositioningProvider(this);
        platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(android.location.Location location) {
                userMarker.setCoordinates(new GeoCoordinates(location.getLatitude(), location.getLongitude()));

            }
        });

        my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.getCamera().lookAt(userMarker.getCoordinates());
            }
        });

        swipe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        handleAndroidPermissions();
    }




    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(this);
        permissionsRequestor.request(new ResultListener(){

            @Override
            public void permissionsGranted() {
                loadMapScene();
            }

            @Override
            public void permissionsDenied() {
                Log.e(TAG, "Permissions denied by user.");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsRequestor.onRequestPermissionsResult(requestCode, grantResults);
    }

    private void loadMapScene() {
        // Load a scene from the HERE SDK to render the map with a map scheme.
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapError mapError) {
                if (mapError == null) {
                    double distanceInMeters = 1000 * 10;
                    mapView.getCamera().lookAt(
                            new GeoCoordinates(36.88675, -76.30570), distanceInMeters);
                } else {
                    Log.d(TAG, "Loading map failed: mapError: " + mapError.name());
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }



}