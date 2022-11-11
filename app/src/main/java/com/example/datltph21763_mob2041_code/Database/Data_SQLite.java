package com.example.datltph21763_mob2041_code.Database;

public class Data_SQLite {
    public static final String INSERT_INTO_THU_THU = "Insert into ThuThu(MaTT, HoTen, MatKhau) values\n" +
            "('admin','Dat Admin','admin'),\n" +
            "('datlt','Nguyen dat','123456'),\n" +
            "('teonv','Nguyen van teo','2345'),\n" +
            "('not','Nguyen Vdmin','admin')";
    public static final String INSERT_INTO_THANH_VIEN = "Insert into thanhvien(soTK,hoTen,namSinh) VALUES\n" +
            "('01923','Nguyen Van A','2003'),\n" +
            "('2344','Nguyen Van B','2002'),\n" +
            "('2455511','Nguyen Van C','2004'),\n" +
            "('45522','Nguyen Van D','2005'),\n" +
            "('23444','Nguyen Van E','2006'),\n" +
            "('24445','Nguyen Van F','2007')";
    public static final String INSERT_INTO_SACH = "INSERT INTO Sach(tenSach,giaThue,maLoai) VALUES\n" +
            "('Lập trình cơ bản','2000','2'),\n" +
            "('Tiếng Anh','4000','2'),\n" +
            "('Lập trình cơ bản','5000','1'),\n" +
            "('Photoshop','3000','2'),\n" +
            "('Lập trình nâng cao','2000','3'),\n" +
            "('Lập trình cơ bản','2000','3'),\n" +
            "('Tiếng Anh','3000','2'),\n" +
            "('Photoshop','4000','4'),\n" +
            "('Tiếng Anh','6000','3')";
    public static final String INSERT_INTO_LOAI_SACH = "insert into LoaiSach(tenLoai) VALUES\n" +
            "('Lập trình cơ bản'),\n" +
            "('Lập trình nâng cao'),\n" +
            "('Tiếng Anh'),\n" +
            "('Photoshop')";
}
