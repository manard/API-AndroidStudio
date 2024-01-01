package com.manar.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class url1Activity extends AppCompatActivity {//This Activity to display the names of recipes according to the type of food

    private RequestQueue queue;
   private Spinner spn;//spinner contain some types of food
    private Button btn1;//ok button
    private ListView lstView;//to display recipes in it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url1);

        queue = Volley.newRequestQueue(this);
        spn=findViewById(R.id.spn);
        btn1 = findViewById(R.id.btn1);
        lstView=findViewById(R.id.lstView);
        Intent intent=getIntent();

       btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "wait please !", Toast.LENGTH_SHORT).show();
                String query = spn.getSelectedItem().toString();
                String appId = "4e9de26a";//Edamam id
                String appKey = "c7a9f9b9c28cd6353ffba3f1f27ed903";//Edamam key
                String apiUrl = "https://api.edamam.com/search?q=" + query + "&app_id=" + appId + "&app_key=" + appKey;//Edamam url
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ArrayList<String> list = new ArrayList<>();
                                try {
                                    JSONArray arrs = response.getJSONArray("hits");
                                    for (int i = 0; i < arrs.length(); i++) {
                                        JSONObject obj = arrs.getJSONObject(i);
                                        JSONObject recipe = obj.getJSONObject("recipe");
                                        String recipeName = recipe.getString("label");//to take the title(label) of recipes
                                        list.add(recipeName );//add recipes in list
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(url1Activity.this,
                                        android.R.layout.simple_list_item_1, list);
                                lstView.setAdapter(adapter);//add recipes in list View
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VolleyError", "Error: " + error.toString());
                            }
                        });
                queue.add(request);
       }
        });
    }



}
