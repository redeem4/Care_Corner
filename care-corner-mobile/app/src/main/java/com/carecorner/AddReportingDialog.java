package com.carecorner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class AddReportingDialog extends AppCompatDialogFragment {

    private EditText reportingName;
    private AddReportingDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.reporting_add_dialog, null);
        builder.setView(view)
                .setTitle("Add Resources Entry")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = reportingName.getText().toString();
                        //String password = journalEntry.getText().toString();
                        listener.applyTexts(username);
                    }
                });
        reportingName = view.findViewById(R.id.reporting_name);
        //journalEntry = view.findViewById(R.id.journal_text2);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddReportingDialog.AddReportingDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement Add Reporting Entry Dialog");
        }
    }

    public interface AddReportingDialogListener {
        void applyTexts(String reportingName);

    }


}
