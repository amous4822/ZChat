package com.example.project.zchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final ArrayList<String> messages = new ArrayList<>();
        final Button save = findViewById(R.id.button2);
        final EditText txt = findViewById(R.id.editText2);
        final ListView show = findViewById(R.id.list1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getInput = txt.getText().toString();

                messages.add(getInput);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, messages);
                show.setAdapter(adapter);
                txt.setText(" ");

            }
        });

    }
}
