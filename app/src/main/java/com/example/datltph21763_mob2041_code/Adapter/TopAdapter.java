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

import com.example.datltph21763_mob2041_code.Frag.TopFragment;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.Model.Top;
import com.example.datltph21763_mob2041_code.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> list;
    TextView tvSach, tvSoLuong;
    ImageView imgDel;

    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.topFragment = topFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item, null);
        }
            final Top item = list.get(position);
            if(item != null){
            tvSach = v.findViewById(R.id.tvTopSach);
            tvSach.setText("Sách: " + item.getTenSach());

            tvSoLuong = v.findViewById(R.id.tvTopSl);
            tvSoLuong.setText("Số Lượng: " + item.getSoLuong());
        }
        return v;
    }

}
