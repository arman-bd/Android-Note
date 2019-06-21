package com.softopian.simplenote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    String id;
    Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dataBaseHelper = new DataBaseHelper(this);

        id = getIntent().getExtras().getString("id");
        Cursor cursor = dataBaseHelper.getSingle(id);

        TextView title = findViewById(R.id.titleView);
        TextView body = findViewById(R.id.bodyView);
        btnEdit = findViewById(R.id.editButton);
        btnDelete = findViewById(R.id.editDelete);

        cursor.moveToNext();
        String titleData = cursor.getString(1);
        String bodyData = cursor.getString(2);

        title.setText(titleData);
        body.setText(bodyData);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewActivity.this);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Do you want to delete this?");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataBaseHelper.Delete(id);
                        Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton("Cancel", null);
                alertDialog.setCancelable(true);
                alertDialog.show();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
        this.finish();
    }
}
