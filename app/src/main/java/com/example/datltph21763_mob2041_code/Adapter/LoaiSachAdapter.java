package com.example.datltph21763_mob2041_code.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datltph21763_mob2041_code.Frag.LoaiSachFragment;
import com.example.datltph21763_mob2041_code.Frag.ThanhVienFragment;
import com.example.datltph21763_mob2041_code.Model.LoaiSach;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment loaiSachFragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLS, tvTenLS;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment loaiSachFragment, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.loaiSachFragment = loaiSachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);

        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLS = v.findViewById(R.id.tvMaLS);
            tvMaLS.setText("Mã Loại Sách: " + item.getMaLoai());
            tvTenLS = v.findViewById(R.id.tvTenLS);
            tvTenLS.setText("Tên Loại Sách: " + item.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loaiSachFragment.xoa(String.valueOf(item.getMaLoai()));
                }
            });

        }
        return v;
    }
}
