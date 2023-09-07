package com.bam.asps;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bam.asps.Adapter.AdapterRecyclerViewRembugLaporanAnggota;
import com.bam.asps.Adapter.AdapterRecyclerViewRembugPenerimaan;
import com.bam.asps.Model.ModelRembugLaporanAnggota;
import com.bam.asps.Model.ModelRembugPenerimaan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class LaporanAnggotaRembugActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerViewRembugLaporanAnggota recyclerViewAdapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_anggota_rembug);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String Cabang = user.get(SessionManager.ids_cabang);

        processdata(Cabang);
    }

    public void processdata(String cabang){
        StringRequest request = new StringRequest(Request.Method.POST, DbContract.GET_DATA_REMBUG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson =builder.create();

                ModelRembugLaporanAnggota data[] = gson.fromJson(response, ModelRembugLaporanAnggota[].class);

                AdapterRecyclerViewRembugLaporanAnggota adapter = new AdapterRecyclerViewRembugLaporanAnggota(LaporanAnggotaRembugActivity.this,data);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kode_cabang", cabang);
                params.put("tujuan", "rv");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}