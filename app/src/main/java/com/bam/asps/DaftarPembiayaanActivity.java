package com.bam.asps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class DaftarPembiayaanActivity extends AppCompatActivity {

    EditText besarPinjam, jangkaWaktu, angsurPinjam, besarMargin, angsurMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pembiayaan);

        besarPinjam = findViewById(R.id.besarPinjam);
        jangkaWaktu = findViewById(R.id.jangkaPinjam);
        angsurPinjam = findViewById(R.id.angsurPinjam);
        besarMargin = findViewById(R.id.besarMargin);
        angsurMargin = findViewById(R.id.angsurMargin);
        besarPinjam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               if (!editable.toString().equals("")){
                    String konversi, jangkawaktu = "";
                    besarPinjam.removeTextChangedListener(this);

                    konversi = besarPinjam.getText().toString().replace(".","");

                    jangkawaktu = jangkaWaktu.getText().toString();

                    besarPinjam.setText(formatRupiah(String.valueOf(editable)));
                    besarPinjam.setSelection(formatRupiah(String.valueOf(editable)).length());
                    besarPinjam.addTextChangedListener(this);
                    angsurPinjam.setText(String.valueOf(perhitunganangsur(konversi,jangkawaktu)));
                }

            }
        });

        besarMargin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().equals("")){
                    besarMargin.removeTextChangedListener(this);

                    String konversi = besarMargin.getText().toString().replace(".","");

                    String jangkawaktu = jangkaWaktu.getText().toString();

                    besarMargin.setText(formatRupiah(String.valueOf(editable)));
                    besarMargin.setSelection(formatRupiah(String.valueOf(editable)).length());
                    besarMargin.addTextChangedListener(this);
                    angsurMargin.setText(String.valueOf(perhitunganangsur(konversi,jangkawaktu)));
                }
            }
        });
    }

    private double perhitunganangsur(String nilai1, String nilai2){
        double angsurpinjaman;
        angsurpinjaman = Double.parseDouble(nilai1)/Double.parseDouble(nilai2);
        return angsurpinjaman;
    }

    private String formatRupiah(String angka){
        String current = "";
        Locale local = new Locale("id", "id");
        String replaceable = String.format("[.\\s]",
                NumberFormat.getCurrencyInstance().getCurrency().getSymbol(local));
        String cleanString = angka.toString().replaceAll(replaceable,"");
        double parsed;
        try {
            parsed = Double.parseDouble(cleanString);
        } catch (NumberFormatException e) {
            parsed = 0.00;
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
        formatter.setMaximumFractionDigits(0);
        formatter.setParseIntegerOnly(true);
        String formatted = formatter.format((parsed));
        String replace = String.format("[Rp\\s]",NumberFormat.getCurrencyInstance().getCurrency().getSymbol(local));
        String clean = formatted.replaceAll(replace, "");
        current = formatted;
        return clean;
    }
}