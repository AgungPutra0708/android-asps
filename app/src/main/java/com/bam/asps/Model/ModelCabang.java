package com.bam.asps.Model;

public class ModelCabang {
    String id_cabang, nama_cabang, created_date, status_cabang;

    public ModelCabang() {
    }

    public ModelCabang(String id_cabang, String nama_cabang, String created_date, String status_cabang) {
        this.id_cabang = id_cabang;
        this.nama_cabang = nama_cabang;
        this.created_date = created_date;
        this.status_cabang = status_cabang;
    }

    public String getId_cabang() {
        return id_cabang;
    }

    public void setId_cabang(String id_cabang) {
        this.id_cabang = id_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getStatus_cabang() {
        return status_cabang;
    }

    public void setStatus_cabang(String status_cabang) {
        this.status_cabang = status_cabang;
    }
}
