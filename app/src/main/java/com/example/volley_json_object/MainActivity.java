package com.example.volley_json_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);
        Button buttonParse = findViewById(R.id.btn_parse);
        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JasonParse();
            }
        });
    }



    private void JasonParse() {
        String url = "https://run.mocky.io/v3/753da801-3814-4c83-9268-c6adc0a624db";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("users");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject users = jsonArray.getJSONObject(i);
                                int id=users.getInt("id");
                                String name = users.getString("name");
                                String email = users.getString("email");
                                String gender =users.getString("gender");
                               // String contact=users.getString("contact.mobile");
                                //String contact1=users.getString("contact.home");
                                //String contact2=users.getString("contact.office");
                                tv.append(String.valueOf(id)+
                                        ", "
                                        +name+  ", "
                                        +gender+
                                        ", "
                                        +email +


                                        "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}