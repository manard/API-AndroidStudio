package com.manar.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class url2Activity extends AppCompatActivity {//This Activity to display the names and prices of the products
    private RequestQueue queue;
    private ListView lstView;//to display product^s title and price in it
    private Button btn;//to show products
    ArrayList<String> product = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url2);
        queue = Volley.newRequestQueue(this);
        lstView=findViewById(R.id.lstView);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://dummyjson.com/products";//dummyjson url

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray array=response.getJSONArray("products");
                                    for(int i=0 ; i<30;i++){
                                        JSONObject obj=array.getJSONObject(i);
                                        String title = obj.getString("title");//get title of product
                                        String price = obj.getString("price");//get price of product
                                        product.add(title+" , Price: $" + price);//add title and price in product list
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(url2Activity.this,
                                        android.R.layout.simple_list_item_1, product);
                                lstView.setAdapter(adapter);
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley Error", "Error making API request: " + error.toString());
                            }
                        });

                queue.add(jsonObjectRequest);

            }
        });

    }




}