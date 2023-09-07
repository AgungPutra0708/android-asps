package com.bam.asps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setTitle("Menu Dashboard");
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String CABANGS = intent.getStringExtra("ID_CABANGS");

        sessionManager = new SessionManager(getApplicationContext());

        CardView dataAnggota = (CardView) findViewById(R.id.inputdataanggota);
        dataAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarAnggotaActivity.class);
                startActivity(intent);
            }
        });

        CardView dataPenerimaan = (CardView) findViewById(R.id.inputpenerimaan);
        dataPenerimaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PenerimaanRembugActivity.class);
                startActivity(intent);
            }
        });

        CardView cardKredit = (CardView) findViewById(R.id.skor);
        cardKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KreditRembugActivity.class);
                startActivity(intent);
            }
        });

        CardView cardPembiayaan = (CardView) findViewById(R.id.inputdatapembiayaan);
        cardPembiayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaftarPembiayaanActivity.class);
                startActivity(intent);
            }
        });

        CardView cardLaporanAnggota = (CardView) findViewById(R.id.simpanan);
        cardLaporanAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LaporanAnggotaRembugActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.item_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:{
                new AlertDialog.Builder(MainActivity.this)
                .setTitle("Logout")
                        .setMessage("Apakah anda yakin akan keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sessionManager.logout();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        }
        return super.onOptionsItemSelected(item);
    }
}