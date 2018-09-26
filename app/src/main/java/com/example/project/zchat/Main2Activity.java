package com.example.project.zchat;

import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    final ArrayList<String> messages = new ArrayList<>();
    final Button save = findViewById(R.id.button2);
    final EditText txt = findViewById(R.id.editText2);
    final ListView show = findViewById(R.id.list1);

    RequestQueue queue = Volley.newRequestQueue(this);
    private static final String endpoint = "https://postman-echo.com/encoding/utf8?message=Text";
    private String msgResponse;

    /********************ADDING REQUEST TO RECEIVE DATA**********************/

    // adding GET request
    private String makeJsonObjReq() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                endpoint,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("POST: ", response.toString());

                        try{
                            msgResponse = response.getString("message");
                        } catch (Exception e){
                            System.out.println("error in response");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Main2Activity.this, "Some error occurred !!", Toast.LENGTH_SHORT).show();
                VolleyLog.d("POST: ", "Error: " + error.getMessage());
            }
        }) {

            //Passing some request headers, probably not necessary
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        queue.add(jsonObjReq);
        return msgResponse;
    }

    /********************ADDING REQUEST TO SEND DATA**********************/


    //adding POST request
    private void sendJsonObjReq (final String message){

    StringRequest stringRequest = new StringRequest(Request.Method.POST, endpoint,

            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    txt.setText("");
                    Toast.makeText(Main2Activity.this, "message sent !!", Toast.LENGTH_SHORT).show();
                    VolleyLog.d("POST: ", "Response :" + response);

                }
            }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(Main2Activity.this, "Some error occurred !!", Toast.LENGTH_SHORT).show();
            VolleyLog.d("POST: ", "Error: " + error.getMessage());

        }
    })

    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> values = new HashMap<>();
            values.put("message",message);
            return values;
        }
    };

    queue.add(stringRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getInput = txt.getText().toString();

                if (! getInput.equals("")) {

                    sendJsonObjReq(getInput);

                    getInput = makeJsonObjReq();
                }

                messages.add(getInput);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, messages);
                show.setAdapter(adapter);
                txt.setText(" ");

            }
        });
    }
}
