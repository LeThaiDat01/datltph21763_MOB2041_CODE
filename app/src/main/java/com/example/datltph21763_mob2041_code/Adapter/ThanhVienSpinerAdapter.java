package com.example.datltph21763_mob2041_code.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datltph21763_mob2041_code.Model.Sach;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.R;

import java.util.ArrayList;

public class ThanhVienSpinerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;

    public ThanhVienSpinerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaTV() + ". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
}
