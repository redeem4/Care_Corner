package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PanicActivity extends AppCompatActivity {

    private ConstraintLayout mapSheet;
    private BottomSheetBehavior bottomSheetBehavior;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        mapSheet = findViewById(R.id.map_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(mapSheet);
    }
}