package com.example.datltph21763_mob2041_code.Frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.datltph21763_mob2041_code.Adapter.LoaiSachAdapter;
import com.example.datltph21763_mob2041_code.Adapter.ThanhVienAdapter;
import com.example.datltph21763_mob2041_code.DAO.LoaiSachDAO;
import com.example.datltph21763_mob2041_code.DAO.ThanhVienDAO;
import com.example.datltph21763_mob2041_code.Model.LoaiSach;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {

    ListView lvLoaiSach;
    ArrayList<LoaiSach> list;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLs, edTenLs;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sachragment, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fabLs);
        dao = new LoaiSachDAO(getActivity());
        capNhapLV();

        return v;
    }

    void capNhapLV() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capNhapLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

}