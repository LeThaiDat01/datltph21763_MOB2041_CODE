package com.example.datltph21763_mob2041_code.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.Model.ThuThu;
import com.example.datltph21763_mob2041_code.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu tt) {
        ContentValues values = new ContentValues();
//        values.put("maTT", tt.getMaTT());
        values.put("hoTen", tt.getHoTen());
        values.put("matKhau", tt.getMatKhau());
        return db.insert("ThuThu", null, values);
    }

    public int update(ThuThu tt) {
        ContentValues values = new ContentValues();
        values.put("hoTen", tt.getHoTen());
        values.put("matKhau", tt.getMatKhau());
        return db.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(tt.getMaTT())});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu tt = new ThuThu();
            tt.setMaTT(c.getString(c.getColumnIndex("maTT")));
            tt.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            tt.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(tt);
        }
        return list;


    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    //check login
    public int checkLogin(String id, String passWord) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, passWord);
        if (list.size() == 0) {
            return -1;
        }
        return 1;

    }
}
