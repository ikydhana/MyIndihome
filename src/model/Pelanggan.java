package model;

import javax.swing.table.TableModel;

public class Pelanggan {
    private int id_pelanggan;
    private String nama_pelanggan;
    private String paket;
    private String alamat_email;
    private String no_aktif;
    private String no_alternatif;

    public Pelanggan () {
    
}

    public Pelanggan(int id_pelanggan, String nama_pelanggan, String paket, String alamat_email, String no_aktif, String no_alternatif) {
        this.id_pelanggan = id_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        this.paket = paket;
        this.alamat_email = alamat_email;
        this.no_aktif = no_aktif;
        this.no_alternatif = no_alternatif;
    }

    public Pelanggan(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getAlamat_email() {
        return alamat_email;
    }

    public void setAlamat_email(String alamat_email) {
        this.alamat_email = alamat_email;
    }

    public String getNo_aktif() {
        return no_aktif;
    }

    public void setNo_aktif(String no_aktif) {
        this.no_aktif = no_aktif;
    }

    public String getNo_alternatif() {
        return no_alternatif;
    }

    public void setNo_alternatif(String no_alternatif) {
        this.no_alternatif = no_alternatif;
    }

    public Object getNama_Pelanggan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getId_Pelanggan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSelectedRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TableModel getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNama_Pelanggan(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId_Pelanggan(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}


