package com.softopian.simplenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dataBaseHelper = new DataBaseHelper(this);

        btnSave = findViewById(R.id.saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = findViewById(R.id.addTitle);
                EditText body = findViewById(R.id.addBody);

                long Inserted = dataBaseHelper.Insert(title.getText().toString(), body.getText().toString());
                if(Inserted != -1){
                    Toast.makeText(getApplicationContext(), "Note Created", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                    intent.putExtra("id", Long.toString(Inserted));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
        this.finish();
    }
}
