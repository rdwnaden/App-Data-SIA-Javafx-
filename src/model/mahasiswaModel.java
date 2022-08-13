/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author IT SUPPORT
 */
public class mahasiswaModel {
    int id_mahasiswa;
    String nama, nim, prodi, alamat, dosbing;

    public mahasiswaModel(int id_mahasiswa, String nama, String nim, String prodi, String alamat, String dosbing) {
        this.id_mahasiswa = id_mahasiswa;
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.alamat = alamat;
        this.dosbing = dosbing;
    }

    public int getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(int id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDosbing() {
        return dosbing;
    }

    public void setDosbing(String dosbing) {
        this.dosbing = dosbing;
    }
}
