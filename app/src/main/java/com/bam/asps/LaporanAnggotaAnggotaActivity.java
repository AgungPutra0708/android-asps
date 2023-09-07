package com.bam.asps;

import android.annotation.SuppressLint;
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
import com.bam.asps.Adapter.AdapterRecyclerViewAnggotaLaporanAnggota;
import com.bam.asps.Adapter.AdapterRecyclerViewAnggotaPenerimaan;
import com.bam.asps.Model.ModelAnggotaPenerimaan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class LaporanAnggotaAnggotaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerViewAnggotaLaporanAnggota recyclerViewAdapter;
    SessionManager sessionManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_anggota_anggota);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//        Intent intent = getIntent();
//        String Rembugs = intent.getStringExtra("TEKS_REMBUG");
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String Rembugs = user.get(SessionManager.ids_rembug);

        processdata(Rembugs);
    }
    public void processdata(String rembug){
        StringRequest request = new StringRequest(Request.Method.POST, DbContract.GET_DATA_ANGGOTA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson =builder.create();

                ModelAnggotaPenerimaan data[] = gson.fromJson(response, ModelAnggotaPenerimaan[].class);

                AdapterRecyclerViewAnggotaLaporanAnggota adapter = new AdapterRecyclerViewAnggotaLaporanAnggota(LaporanAnggotaAnggotaActivity.this,data);
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
                params.put("kode_rembug", rembug);
                params.put("tujuan", "rv");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}