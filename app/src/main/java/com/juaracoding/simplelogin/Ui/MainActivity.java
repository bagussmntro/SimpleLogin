package com.juaracoding.simplelogin.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.juaracoding.simplelogin.Model.Data;
import com.juaracoding.simplelogin.R;
import com.juaracoding.simplelogin.Service.APIClient;
import com.juaracoding.simplelogin.Service.APIInterfaceRest;
import com.juaracoding.simplelogin.Service.SharedPreferencesUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SharedPreferencesUtil session;
    EditText txtusername, txtpassword;
    Button btnLogin;
    Data dataResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_dark);

        session = new SharedPreferencesUtil(MainActivity.this);
        if (!session.getUsername().equals("")) {
            Intent intent = new Intent(MainActivity.this, WebApp.class);
            startActivity(intent);
            finish();
        }

        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAPI();
                Intent it = new Intent(MainActivity.this,WebApp.class);
                startActivity(it);
            }
        });
    }

    APIInterfaceRest apiInterfaceRest;
    ProgressDialog progressDialog;

    public void LoginAPI() {
        apiInterfaceRest = APIClient.getClient().create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();


        Call<Data> mulaiRequest = apiInterfaceRest.requestLogin(txtusername.getText().toString(), txtpassword.getText().toString());
        mulaiRequest.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                progressDialog.dismiss();
              dataResponse = response.body();
                if (dataResponse != null) {
                    kondisiLogin();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

            }
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    private void kondisiLogin() {
        if (dataResponse.getStatus().equals("OK")) {
            session.setUsername(txtusername.getText().toString());
            Intent intent = new Intent(MainActivity.this, WebApp.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(MainActivity.this, dataResponse.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

