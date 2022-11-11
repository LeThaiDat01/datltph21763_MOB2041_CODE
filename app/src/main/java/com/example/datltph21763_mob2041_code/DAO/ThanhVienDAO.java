package com.example.datltph21763_mob2041_code.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien tv) {
        ContentValues values = new ContentValues();
//        values.put("maTV", tv.getMaTV());
        values.put("soTK",tv.getSoTK());
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());

        return db.insert("ThanhVien", null, values);

    }

    public int update(ThanhVien tv) {
        ContentValues values = new ContentValues();
        values.put("soTK",tv.getSoTK());
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());

        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(tv.getMaTV())});
    }


    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThanhVien tv = new ThanhVien();
            tv.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            tv.setSoTK(Integer.parseInt(c.getString(c.getColumnIndex("soTK"))));
            tv.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            tv.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
             Log.i("zzzzzzzzzzz",tv.toString());

            list.add(tv);
        }
        return list;
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }
    public ArrayList<String> select_name(){
        Cursor cursor = db.rawQuery("SELECT hoTen FROM ThanhVien WHERE hoTen LIKE '%n%' ",null);
        ArrayList<String> ten = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){
            ten.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return ten;
    }

}
