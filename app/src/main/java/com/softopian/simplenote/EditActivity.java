package com.softopian.simplenote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;


    EditText title;
    EditText body;
    Button btnSave;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dataBaseHelper = new DataBaseHelper(this);

        id = getIntent().getExtras().getString("id");
        Cursor cursor = dataBaseHelper.getSingle(id);

        title = findViewById(R.id.editTitle);
        body = findViewById(R.id.editBody);
        btnSave = findViewById(R.id.editSaveButton);

        cursor.moveToNext();
        String titleData = cursor.getString(1);
        String bodyData = cursor.getString(2);

        title.setText(titleData);
        body.setText(bodyData);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = title.getText().toString();
                String newBody = body.getText().toString();

                dataBaseHelper.Update(id, newTitle, newBody);

                Toast.makeText(getApplicationContext(), "Note Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        this.finish();
    }
}
