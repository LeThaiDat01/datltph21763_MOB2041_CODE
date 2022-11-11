package com.example.datltph21763_mob2041_code.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datltph21763_mob2041_code.Model.LoaiSach;
import com.example.datltph21763_mob2041_code.Model.ThuThu;
import com.example.datltph21763_mob2041_code.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach ls) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", ls.getTenLoai());
        return db.insert("LoaiSach", null, values);
    }

    public int update(LoaiSach ls) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", ls.getTenLoai());

        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(ls.getMaLoai())});
    }

    public int delete(String id) {
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            LoaiSach ls = new LoaiSach();
            ls.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            ls.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(ls);
        }
        return list;
    }

    public List<LoaiSach> getAll() {
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }
}
