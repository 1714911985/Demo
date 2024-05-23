package com.example.demo.main.fourth.networktechnology;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;
import com.example.demo.main.fourth.networktechnology.auxiliary.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParseJsonActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnJsonObject;
    private Button btnGson;
    private final static int JSON_OBJECT = 0;
    private final static int GSON = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        initView();

        Toolbar toolbar = findViewById(R.id.tb_parse_json);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initView() {
        btnJsonObject = findViewById(R.id.btn_json_object);
        btnJsonObject.setOnClickListener(this);
        btnGson = findViewById(R.id.btn_gson);
        btnGson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_json_object) {
            sendRequestWithOkHttp(JSON_OBJECT);
        } else if (v.getId() == R.id.btn_gson) {
            sendRequestWithOkHttp(GSON);
        }
    }

    private void sendRequestWithOkHttp(int model) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.0.10/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.body() != null) {
                        String responseData = response.body().string();
                        if (responseData != null) {
                            switch (model) {
                                case JSON_OBJECT:
                                    parseJSONWithJSONObject(responseData);
                                    break;
                                case GSON:
                                    parseJSONWithGSON(responseData);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String responseData) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");

                Log.e("JSONObject", "id : " + id);
                Log.e("JSONObject", "name : " + name);
                Log.e("JSONObject", "version : " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        Type typeOf = new TypeToken<List<App>>() {
        }.getType();
        List<App> appList = gson.fromJson(responseData, typeOf);
        for (App app : appList) {
            Log.e("Gson", "id : " + app.getId());
            Log.e("Gson", "name : " + app.getName());
            Log.e("Gson", "version : " + app.getVersion());
        }
    }

}