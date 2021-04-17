package com.carecorner;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanicDeactiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanicDeactiveFragment extends Fragment implements View.OnClickListener {

    private NavController panicNavController;
    private Button mapBtn;





    public PanicDeactiveFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_panic_deactive, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        panicNavController = Navigation.findNavController(view);
        mapBtn = view.findViewById(R.id.panic_map_btn);
        mapBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.panic_map_btn:
                panicNavController.navigate(R.id.action_panicDeactiveFragment_to_panicMapFragment);
                break;
        }
    }
}