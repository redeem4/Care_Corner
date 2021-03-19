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

public class AddJournalEntryDialog extends AppCompatDialogFragment {
    private EditText journalName, journalEntry;
    private AddJournalEntryDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.journal_entry_add_dialog, null);
        builder.setView(view)
                .setTitle("Add Journal Entry")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = journalName.getText().toString();
                        String password = journalEntry.getText().toString();
                        listener.applyTexts(username, password);
                    }
                });
        journalName = view.findViewById(R.id.journal_name);
        journalEntry = view.findViewById(R.id.journal_text2);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddJournalEntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement Add Journal Entry Dialog");
        }
    }

    public interface AddJournalEntryDialogListener {
        void applyTexts(String journalName, String journalEntry);
    }
}