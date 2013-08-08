package com.example.personalAssistant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartUp extends Activity {
    private EditText VWSpamText;
    private Button VWSubmit;
    private PersonalAssistantDatastore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        InitializeDB();
        InitializeViewElements();
              VWSubmit.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String spamText = VWSpamText.getText().toString();
                      db.update(spamText);
                  }
              });
    }

    private void InitializeDB() {
        db = new PersonalAssistantDatastore(getApplicationContext());
    }

    private void InitializeViewElements() {
        VWSpamText = (EditText) findViewById(R.id.editText);
        VWSubmit = (Button) findViewById(R.id.button);
    }
}
