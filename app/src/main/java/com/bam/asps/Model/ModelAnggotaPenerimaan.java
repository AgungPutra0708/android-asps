package com.bam.asps.Model;

public class ModelAnggotaPenerimaan {
    String id_anggota, kode_rempug, kode_cabang, nama_anggota;

    public ModelAnggotaPenerimaan() {
    }

    public ModelAnggotaPenerimaan(String id_anggota, String kode_rempug, String kode_cabang, String nama_anggota) {
        this.id_anggota = id_anggota;
        this.kode_rempug = kode_rempug;
        this.kode_cabang = kode_cabang;
        this.nama_anggota = nama_anggota;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getKode_rempug() {
        return kode_rempug;
    }

    public void setKode_rempug(String kode_rempug) {
        this.kode_rempug = kode_rempug;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }
}
