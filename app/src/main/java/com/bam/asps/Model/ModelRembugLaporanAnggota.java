package com.bam.asps.Model;

public class ModelRembugLaporanAnggota {
    String id_rempug,kode_cabang,kode_kota,kode_kec,kode_kel,kode_rempug,nama_rempug,jmlhanggota;

    public ModelRembugLaporanAnggota() {
    }

    public ModelRembugLaporanAnggota(String id_rempug, String kode_cabang, String kode_kota, String kode_kec, String kode_kel, String kode_rempug, String nama_rempug, String jmlhanggota) {
        this.id_rempug = id_rempug;
        this.kode_cabang = kode_cabang;
        this.kode_kota = kode_kota;
        this.kode_kec = kode_kec;
        this.kode_kel = kode_kel;
        this.kode_rempug = kode_rempug;
        this.nama_rempug = nama_rempug;
        this.jmlhanggota = jmlhanggota;
    }

    public String getId_rempug() {
        return id_rempug;
    }

    public void setId_rempug(String id_rempug) {
        this.id_rempug = id_rempug;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }

    public String getKode_kota() {
        return kode_kota;
    }

    public void setKode_kota(String kode_kota) {
        this.kode_kota = kode_kota;
    }

    public String getKode_kec() {
        return kode_kec;
    }

    public void setKode_kec(String kode_kec) {
        this.kode_kec = kode_kec;
    }

    public String getKode_kel() {
        return kode_kel;
    }

    public void setKode_kel(String kode_kel) {
        this.kode_kel = kode_kel;
    }

    public String getKode_rempug() {
        return kode_rempug;
    }

    public void setKode_rempug(String kode_rempug) {
        this.kode_rempug = kode_rempug;
    }

    public String getNama_rempug() {
        return nama_rempug;
    }

    public void setNama_rempug(String nama_rempug) {
        this.nama_rempug = nama_rempug;
    }

    public String getJmlhanggota() {
        return jmlhanggota;
    }

    public void setJmlhanggota(String jmlhanggota) {
        this.jmlhanggota = jmlhanggota;
    }
}
