package com.example.datltph21763_mob2041_code.Model;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;
    private int soTK;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh, int soTK) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.soTK = soTK;
    }

    public int getSoTK() {
        return soTK;
    }

    public void setSoTK(int soTK) {
        this.soTK = soTK;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                '}';
    }
}
