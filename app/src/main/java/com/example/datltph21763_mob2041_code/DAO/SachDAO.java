package com.example.datltph21763_mob2041_code.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datltph21763_mob2041_code.Model.LoaiSach;
import com.example.datltph21763_mob2041_code.Model.Sach;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach s) {
        ContentValues values = new ContentValues();
        values.put("tenSach", s.getTenSach());
        values.put("giaThue", s.getGiaThue());
        values.put("maLoai", s.getMaLoai());
        return db.insert("Sach", null, values);
    }

    public int update(Sach s) {
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("tenSach", s.getTenSach());
        values.put("giaThue", s.getGiaThue());
        values.put("maLoai", s.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(s.getMaSach())});
    }
    //chay bai di b giữ mới update được b ạ

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach s = new Sach();
            s.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            s.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            s.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            s.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(s);
        }
        return list;

    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

}
