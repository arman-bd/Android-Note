package com.softopian.simplenote;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ArrayList<HashMap<String, String>> noteArrayList;
    ListAdapter adapter;
    FloatingActionButton btnAdd;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnAdd = findViewById(R.id.addButton);
        listView = findViewById(R.id.noteList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView ListID = view.findViewById(R.id.textListID);
                String id = ListID.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onResume()
    {
        super.onResume();

        dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor = dataBaseHelper.getAll();

        noteArrayList = new ArrayList<>();
        adapter = new SimpleAdapter(this, noteArrayList, R.layout.note_list_item,
                new String[]{"id", "title"}, new int[]{R.id.textListID, R.id.textListTitle});
        listView.setAdapter(adapter);

        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                HashMap<String, String> myHash = new HashMap<>();
                myHash.put("id", cursor.getString(0));
                myHash.put("title", cursor.getString(1));
                noteArrayList.add(myHash);
            }
        }

    }

    public void onBackPressed(){
        this.finish();
    }

}
