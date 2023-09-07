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

import com.bam.asps.LaporanAnggotaAnggotaActivity;
import com.bam.asps.Model.ModelRembugLaporanAnggota;
import com.bam.asps.Model.ModelRembugPenerimaan;
import com.bam.asps.PenerimaanAnggotaActivity;
import com.bam.asps.R;
import com.bam.asps.SessionManager;

public class AdapterRecyclerViewRembugLaporanAnggota extends RecyclerView.Adapter<AdapterRecyclerViewRembugLaporanAnggota.myviewholder> {

    ModelRembugLaporanAnggota data[];
    ConstraintLayout parentLayout;
    SessionManager sessionManager;
    private Context context;

    public AdapterRecyclerViewRembugLaporanAnggota(Context context, ModelRembugLaporanAnggota[] data) {
        this.context =context;
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_laporan_anggota_rembug,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.nmRembug.setText(data[position].getNama_rempug());
        holder.idRembug.setText(data[position].getId_rempug());
        holder.jmlhRembug.setText(data[position].getJmlhanggota());
        holder.parentLayout.setOnClickListener(v -> {
//            Toast.makeText(context,data[position].getId_rempug(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LaporanAnggotaAnggotaActivity.class);
                sessionManager = new SessionManager(context);
                sessionManager.createSessionRembug(data[position].getId_rempug());
                context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ConstraintLayout parentLayout;
        TextView idRembug, nmRembug, jmlhRembug;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            idRembug=itemView.findViewById(R.id.id_rembugs);
            nmRembug=itemView.findViewById(R.id.nama_rembug);
            jmlhRembug=itemView.findViewById(R.id.jumlahAnggota1);
            parentLayout=itemView.findViewById(R.id.parentLayout);
        }
    }
}