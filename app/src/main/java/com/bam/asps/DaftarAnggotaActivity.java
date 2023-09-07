package com.bam.asps;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bam.asps.Model.ModelCabang;
import com.bam.asps.Model.ModelRembug;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaftarAnggotaActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private EditText namaAnggota,nikAnggota,tempatLahirAnggota,tanggalLahirAnggota,pekerjaanAnggota,telponAnggota,alamatAnggota,tanggalMasukAnggota;
    private Spinner jenkelAnggota,cabangAnggota,rembugAnggota;
    private TextView textView;
    private Button submitInputData;
    String cabangAnggotas, rembugAnggotas, jenkelAnggotas;
    SessionManager sessionManager;

    //An ArrayList for Spinner Items
    private ArrayList<String> cabangs, rembugs;

    //JSON Array
    private JSONArray result,resultrembug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_anggota);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String Cabangs = user.get(SessionManager.ids_cabang);

        cabangs = new ArrayList<String>();
        cabangAnggota = findViewById(R.id.cabang);
        cabangAnggota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cabangAnggotas = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rembugs = new ArrayList<String>();
        rembugAnggota = findViewById(R.id.rembug);
        rembugAnggota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rembugAnggotas = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        rembugs = new ArrayList<String>();
        jenkelAnggota = findViewById(R.id.jenkel);
        jenkelAnggota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jenkelAnggotas = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        namaAnggota = findViewById(R.id.nama);
        nikAnggota = findViewById(R.id.nik);
        tempatLahirAnggota = findViewById(R.id.tmptLahir);
        tanggalLahirAnggota = findViewById(R.id.tglLahir);
        tanggalLahirAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TampilTanggal();
            }
        });
        tanggalMasukAnggota = findViewById(R.id.tglMasuk);
        tanggalMasukAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TampilTanggalMasuk();
            }
        });
        pekerjaanAnggota = findViewById(R.id.pekerjaan);
        telponAnggota = findViewById(R.id.telp);
        rembugAnggota = findViewById(R.id.rembug);
        alamatAnggota = findViewById(R.id.alamat);

        submitInputData = findViewById(R.id.button2);
        submitInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDaftarAnggota(namaAnggota.getText().toString(),nikAnggota.getText().toString(),tempatLahirAnggota.getText().toString(),tanggalLahirAnggota.getText().toString(),pekerjaanAnggota.getText().toString(),telponAnggota.getText().toString(),jenkelAnggotas,cabangAnggotas,rembugAnggotas,alamatAnggota.getText().toString(),tanggalMasukAnggota.getText().toString());
//            Toast.makeText(getApplicationContext(),tanggalLahirAnggota.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        processdatacabang();
        processdatarembug(Cabangs);
    }
    public void TampilTanggal(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String bulans ="";
                String tahun = ""+datePicker.getYear();
                String bulan = ""+(datePicker.getMonth());
                if (Integer.parseInt(bulan) < 10){
                    bulans = "0"+bulan;
                }else{
                    bulans = bulan;
                }
                String hari = ""+datePicker.getDayOfMonth();
                String text = ""+hari+"/"+bulans+"/"+tahun;
                tanggalLahirAnggota.setText(text);
            }
        });
    }

    public void TampilTanggalMasuk(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String bulans ="";
                String tahun = ""+datePicker.getYear();
                String bulan = ""+(datePicker.getMonth());
                if (Integer.parseInt(bulan) < 10){
                    bulans = "0"+bulan;
                }else{
                    bulans = bulan;
                }
                String hari = ""+datePicker.getDayOfMonth();
                String text = ""+hari+"/"+bulans+"/"+tahun;
                tanggalMasukAnggota.setText(text);
            }
        });
    }

    public void checkDaftarAnggota(final String namaAnggota,final String nikAnggota,final String tempatLahirAnggota,final String tanggalLahirAnggota,final String pekerjaanAnggota,final String telponAnggota,final String jenkelAnggota,final String cabangAnggotas,final String rembugAnggota,final String alamatAnggota,final String tanggalMasukAnggota) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String resp = jsonObject.getString("server_response");
                            if (resp.equals("[{\"status\":\"OK\"}]")) {
                                Toast.makeText(getApplicationContext(), "Pendaftaran Anggota Berhasil", Toast.LENGTH_SHORT).show();
                                clearEditText();
                            } else if(resp.equals("[{\"status\":\"SAMA\"}]")) {
                                Toast.makeText(getApplicationContext(), "Gagal, NIK Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                clearEditText();
                            } else if(resp.equals("[{\"status\":\"KOSONG\"}]")) {
                                Toast.makeText(getApplicationContext(), "Gagal, NIK Kosong", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Pendaftaran Anggota Gagal", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_rempug", rembugAnggota);
                params.put("id_cabang", cabangAnggotas);
                params.put("nik", nikAnggota);
                params.put("nama", namaAnggota);
                params.put("alamat", alamatAnggota);
                params.put("jenkel", jenkelAnggota);
                params.put("pekerjaan", pekerjaanAnggota);
                params.put("tanggal_masuk", tanggalMasukAnggota);
                params.put("telpon", telponAnggota);
                params.put("tempat_lahir", tempatLahirAnggota);
                params.put("tanggal_lahir", tanggalLahirAnggota);

                return params;
            }
        };

        VolleyConnection.getInstance(DaftarAnggotaActivity.this).addToRequestQue(stringRequest);

    }

    public void clearEditText(){
        namaAnggota.setText("");
        nikAnggota.setText("");
        tempatLahirAnggota.setText("");
        tanggalLahirAnggota.setText("");
        tanggalMasukAnggota.setText("");
        pekerjaanAnggota.setText("");
        telponAnggota.setText("");
        alamatAnggota.setText("");
    }

    public void processdatacabang(){
        StringRequest request = new StringRequest(Request.Method.POST, DbContract.GET_DATA_CABANG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j = null;
                try {
                    j = new JSONObject(response);
                    result = j.getJSONArray("result");
                    getCabang(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                params.put("tujuan", "spinner");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void getCabang(JSONArray j){
        cabangs.add("Pilih Cabang");
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                cabangs.add(json.getString("nama_cabang"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cabangAnggota.setAdapter(new ArrayAdapter<String>(DaftarAnggotaActivity.this, android.R.layout.simple_spinner_dropdown_item, cabangs));
    }

    public void processdatarembug(String cabang){
        StringRequest request = new StringRequest(Request.Method.POST, DbContract.GET_DATA_REMBUG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j = null;
                try {
                    j = new JSONObject(response);
                    resultrembug = j.getJSONArray("result");
                    getRembug(resultrembug);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                params.put("tujuan", "spinner");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void getRembug(JSONArray j){
        rembugs.add("Pilih Rembug");
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                rembugs.add(json.getString("nama_rempug"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        rembugAnggota.setAdapter(new ArrayAdapter<String>(DaftarAnggotaActivity.this, android.R.layout.simple_spinner_dropdown_item, rembugs));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}