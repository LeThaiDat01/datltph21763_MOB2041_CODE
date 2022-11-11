package com.example.datltph21763_mob2041_code.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.datltph21763_mob2041_code.Database.Data_SQLite;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ASSMENT";
    private static final int DB_VERSION = 1;
    static final String CREATE_TABLE_THU_THU = "create table ThuThu (\n" +
            "    maTT    TEXT PRIMARY KEY ,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    matKhau TEXT NOT NULL\n" +
            ");";
    //
    static final String CREATE_TABLE_THANH_VIEN = "CREATE TABLE ThanhVien(\n" +
            "maTV Integer PRIMARY KEY AUTOINCREMENT,\n" +
            "soTK Integer NOT NULL,\n" +
            "hoTen text NOT NULL,\n" +
            "namSinh text NOT NULL\n" +
            ")";
    //
    static final String CREATE_TABLE_LOAI_SACH = "create table LoaiSach (\n" +
            "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenLoai TEXT    NOT NULL\n" +
            ");";

    //
    static final String CREATE_TABLE_SACH = "create table Sach (\n" +
            "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenSach TEXT    NOT NULL,\n" +
            "    giaThue INTEGER NOT NULL,\n" +
            "    maLoai  INTEGER REFERENCES LoaiSach (maLoai) \n" +
            ");";
    //
    static final String CREATE_TABLE_PHIEU_MUON = "create table PhieuMuon (\n" +
            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maTT     TEXT    REFERENCES ThuThu (maTT),\n" +
            "    maTV     TEXT    REFERENCES ThanhVien (maTV),\n" +
            "    masach   INTEGER REFERENCES Sach (maSach),\n" +
            "    tienthue INTEGER NOT NULL,\n" +
            "    ngay     DATE    NOT NULL,\n" +
            "    traSach  INTEGER NOT NULL\n" +
            ");\n";

    //
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table thủ thư
        db.execSQL(CREATE_TABLE_THU_THU);
        // Table Thành viên
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        // Tale Sách
        db.execSQL(CREATE_TABLE_SACH);
        //Table loại sách
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //Table Phiếu mượn
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        //insert data
        db.execSQL(Data_SQLite.INSERT_INTO_THU_THU);
        db.execSQL(Data_SQLite.INSERT_INTO_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_INTO_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_INTO_SACH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //
        String dropTableThuThu = "DROP TABLE IF EXISTS ThuThu";
        db.execSQL(dropTableThuThu);
        //
        String dropTableThanhVien = "DROP TABLE IF EXISTS ThanhVien";
        db.execSQL(dropTableThanhVien);
        //
        String dropTableSach = "DROP TABLE IF EXISTS Sach";
        db.execSQL(dropTableSach);
        //
        String dropTableLoaiSach = "DROP TABLE IF EXISTS LoaiSach";
        db.execSQL(dropTableLoaiSach);
        //
        String dropTablePhieuMuon = "DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
