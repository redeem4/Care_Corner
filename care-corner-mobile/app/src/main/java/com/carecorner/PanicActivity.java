package com.carecorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PanicActivity extends AppCompatActivity {

    private ConstraintLayout mapSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageView swipe_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        mapSheet = findViewById(R.id.map_sheet);
        swipe_btn = findViewById(R.id.map_swipe_up);
        bottomSheetBehavior = BottomSheetBehavior.from(mapSheet);

        //prevent map from disappearing at the bottom.
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
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
    }




}