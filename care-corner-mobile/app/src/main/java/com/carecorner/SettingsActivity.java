package com.carecorner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.carecorner.api.ContactApi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {
    //Button
    Button btnSave;

    //Emergency Contact (Name/Phone) (#)
    EditText eCN1, eCN2, eCN3, eCP1, eCP2, eCP3;

    String nm1, nm2, nm3, ph1, ph2, ph3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnSave = findViewById(R.id.btnSaveContacts);
        eCN1 = findViewById(R.id.EmergencyContactName1);
        eCN2 = findViewById(R.id.EmergencyContactName2);
        eCN3 = findViewById(R.id.EmergencyContactName3);
        eCP1 = findViewById(R.id.EmergencyContactNumber1);
        eCP2 = findViewById(R.id.EmergencyContactNumber2);
        eCP3 = findViewById(R.id.EmergencyContactNumber3);

        populateContacts();

        //Create button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContacts();
            }
        });
    }

    private void populateContacts() {
        String userId = CareCornerApplication.getSession().getUserId();
        String contactUrl = CareCornerApplication.getApiRoute("contacts/" + userId);

        AndroidNetworking.get(contactUrl)
                .addHeaders("Content-Type", "application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Response {}:", response.toString());
                            JSONObject data = response.getJSONObject("data");
                            JSONArray contacts = data.getJSONArray("contacts");
                            for (int i = 0; i < contacts.length(); ++i) {
                                JSONObject contact = contacts.getJSONObject(i);
                                String name = contact.getString("name");
                                String phone = contact.getString("phone");
                                String contactId = contact.getString("contact-id");
                                Log.d("Contact: {}", name);
                                // hacky
                                switch (i) {
                                    case 0:
                                        eCN1.setText(name, TextView.BufferType.EDITABLE);
                                        eCP1.setText(phone, TextView.BufferType.EDITABLE);
                                        eCN1.setTag(contactId);
                                        break;
                                    case 1:
                                        eCN2.setText(name, TextView.BufferType.EDITABLE);
                                        eCP2.setText(phone, TextView.BufferType.EDITABLE);
                                        eCN2.setTag(contactId);
                                        break;
                                    case 2:
                                        eCN3.setText(name, TextView.BufferType.EDITABLE);
                                        eCP3.setText(phone, TextView.BufferType.EDITABLE);
                                        eCN3.setTag(contactId);
                                        break;
                                }

                            }
                            ContactApi.updateContacts(contacts);
                        } catch (Exception error) {
                            Log.d("Issue updating contacts:", error.toString());
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Issue with Connection:", error.toString());
                    }
                }) ;
    }

    private void updateContacts() {
        //Retrieves the text from the text box
        nm1 = eCN1.getText().toString();
        nm2 = eCN2.getText().toString();
        nm3 = eCN3.getText().toString();
        ph1 = eCP1.getText().toString();
        ph2 = eCP2.getText().toString();
        ph3 = eCP3.getText().toString();
        String contactId1 = eCN1.getTag().toString();
        String contactId2 = eCN2.getTag().toString();
        String contactId3 = eCN3.getTag().toString();

        try {
            JSONArray contacts = new JSONArray();
            String userId = CareCornerApplication.getSession().getUserId();
            for (int i = 0; i < 3; i++) {
                JSONObject contact = new JSONObject();
                // hacky
                switch (i) {
                    case 0:
                        contact.put("contact-id", contactId1);
                        contact.put("name", nm1);
                        contact.put("phone", ph1);
                        contact.put("user-id", userId);
                        break;
                    case 1:
                        contact.put("contact-id", contactId2);
                        contact.put("name", nm2);
                        contact.put("phone", ph2);
                        contact.put("user-id", userId);
                        break;
                    case 2:
                        contact.put("contact-id", contactId3);
                        contact.put("name", nm3);
                        contact.put("phone", ph3);
                        contact.put("user-id", userId);
                        break;
                }
            }
            ContactApi.updateContacts(contacts);
        } catch(Exception error) {
            Log.d("Issue creating contacts to update:", error.toString());
        }

//        Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
 //       startActivity(intent);
    }

    /**
     * Overrides the Back Button functionality to return to the login screen.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}