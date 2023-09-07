package com.bam.asps.Model;

public class ModelAnggota {
    String id_anggota, id_rempug, id_cabang, nama_anggota;

    public ModelAnggota() {
    }

    public ModelAnggota(String id_anggota, String id_rempug, String id_cabang, String nama_anggota) {
        this.id_anggota = id_anggota;
        this.id_rempug = id_rempug;
        this.id_cabang = id_cabang;
        this.nama_anggota = nama_anggota;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getKode_rempug() {
        return id_rempug;
    }

    public void setKode_rempug(String id_rempug) {
        this.id_rempug = id_rempug;
    }

    public String getKode_cabang() {
        return id_cabang;
    }

    public void setKode_cabang(String id_cabang) {
        this.id_cabang = id_cabang;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }
}
