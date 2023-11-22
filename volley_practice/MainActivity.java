package com.example.volley_practice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Button button;
    EditText userIdEdit,passwordEdit;
    String server_url = "https://jsonplaceholder.typicode.com/posts/3";
    String login_server_url = "http://192.168.137.1/practice/v1/userLogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
        userIdEdit = findViewById(R.id.user_id_edit);
        passwordEdit = findViewById(R.id.password_edit);


        ///////             SET STRING

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StringRequest stringRequest = new StringRequest(
//                        Request.Method.POST,
//                        server_url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                textView.setText(response);
//                                System.out.println(response);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                textView.setText("Error "+error.toString());
//                                error.printStackTrace();
//                            }
//                        });
//                Mysingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
//
//            }
//        });


        ///////                SET IMAGE

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageRequest imageRequest = new ImageRequest(server_url, new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        imageView.setImageBitmap(response);
//                    }
//                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//            }
//        });

        /////////              JSON Request

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                        Request.Method.GET,
//                        server_url,
//                        null,
//                        new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            textView.setText(response.getString("title"));
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                        System.out.println(response);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//
//                Mysingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = userIdEdit.getText().toString();
                String userPassword = passwordEdit.getText().toString();

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        login_server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    textView.setText("your email address is "+ jsonObject.getString("email"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("username",userId);
                        params.put("password",userPassword);
                        return params;
                    }
                };
                Mysingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
            }
        });



    }
}