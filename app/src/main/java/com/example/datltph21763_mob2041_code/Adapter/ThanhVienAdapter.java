package com.example.datltph21763_mob2041_code.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datltph21763_mob2041_code.DAO.ThanhVienDAO;
import com.example.datltph21763_mob2041_code.Frag.ThanhVienFragment;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment thanhVienFragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV, tvNamSinh, tvSoTkTV;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment thanhVienFragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.thanhVienFragment = thanhVienFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item, null);

        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: " + item.getMaTV());
            tvSoTkTV = v.findViewById(R.id.tvSoTkTV);
            tvSoTkTV.setText("Số tài khoản: " + item.getSoTK());
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên Thành viên: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());
            imgDel = v.findViewById(R.id.imgDeleteTV);
            if(item.getSoTK() == 3 ){
//                tvSoTkTV.setTypeface(Typeface.DEFAULT_BOLD);
                tvSoTkTV.setTextColor(Color.RED);
            }
//                if (item.getMaTV() % 2 == 0) {
//                    tvTenTV.setTextColor(Color.GREEN);
//                    tvNamSinh.setTextColor(Color.GREEN);
//                } else {
//                    tvTenTV.setTextColor(Color.RED);
//                    tvNamSinh.setTextColor(Color.RED);
//                }
//                String text = item.getHoTen();
//                SpannableString ss = new SpannableString(text);
//                ForegroundColorSpan span = new ForegroundColorSpan(Color.GREEN);
//                ForegroundColorSpan mauDo = new ForegroundColorSpan(Color.RED);
//
//                String name = item.getHoTen();
//
//                for (int i = 0; i < name.length(); i++) {
//                    if ("N".equalsIgnoreCase(String.valueOf(item.getHoTen().charAt(i)))) {
//                        ss.setSpan(span, i, (i + 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                        Log.d(">>>>>>>>>>>>>>>>>>>>>>", "onBindViewHolder: " + ss);
//                    }
//                    if ("A".equalsIgnoreCase(String.valueOf(item.getHoTen().charAt(i)))) {
//                        ss.setSpan(mauDo, i, (i + 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }else {
//
//                    }
//                    tvTenTV.setText(ss);
//                }
//            ArrayList<String> name1 = new ArrayList<>();
//            ThanhVienDAO dao = new ThanhVienDAO(context);
//            name1 = dao.select_name();
//            Log.d(">>>>>>>>>>>>>>", "onBindViewHolder: "+name1.size());
//
//            for(String o:name1){
//                if(o.equalsIgnoreCase(item.getHoTen())){
//                    Log.d(">>>>>>>>>>>>>>", "onBindViewHolder: "+o);
//                    tvTenTV.setTextColor(Color.RED);
//                }
//            }
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienFragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;

    }

}
