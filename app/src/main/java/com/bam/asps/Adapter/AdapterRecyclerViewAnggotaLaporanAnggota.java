package com.bam.asps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bam.asps.LaporanAnggotaInputActivity;
import com.bam.asps.Model.ModelAnggotaPenerimaan;
import com.bam.asps.PenerimaanInputActivity;
import com.bam.asps.R;

public class AdapterRecyclerViewAnggotaLaporanAnggota extends RecyclerView.Adapter<AdapterRecyclerViewAnggotaLaporanAnggota.ViewHolder> {

    ModelAnggotaPenerimaan data[];
    ConstraintLayout parentLayout1;
//    private ArrayList<ModelAnggota> dataItem1;
    private Context context;

    public AdapterRecyclerViewAnggotaLaporanAnggota(Context context, ModelAnggotaPenerimaan[] data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_laporan_anggota_anggota,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textHead = holder.namaAnggota;
        TextView textSubHead = holder.namaRembug;
        TextView textSubSubHead = holder.jumlahPembiayaan;

        textHead.setText(data[position].getNama_anggota());
        textSubHead.setText(data[position].getKode_rempug());
        textSubSubHead.setText(data[position].getKode_cabang());

        holder.parentLayout.setOnClickListener(v -> {
//            Toast.makeText(context,data[position].getNama_anggota(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LaporanAnggotaInputActivity.class);
            intent.putExtra("Nama_Anggota",data[position].getNama_anggota());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaRembug;
        TextView namaAnggota;
        TextView jumlahPembiayaan;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaAnggota = itemView.findViewById(R.id.nama_anggota);
            namaRembug = itemView.findViewById(R.id.name_rembug);
            jumlahPembiayaan = itemView.findViewById(R.id.pembiayaan_terakhir);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
