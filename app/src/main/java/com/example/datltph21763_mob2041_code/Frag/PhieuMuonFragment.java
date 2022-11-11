package com.example.datltph21763_mob2041_code.Frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datltph21763_mob2041_code.Adapter.PhieuMuonAdapter;
import com.example.datltph21763_mob2041_code.Adapter.SachSpinerAdapter;
import com.example.datltph21763_mob2041_code.Adapter.ThanhVienSpinerAdapter;
import com.example.datltph21763_mob2041_code.DAO.PhieuMuonDAO;
import com.example.datltph21763_mob2041_code.DAO.SachDAO;
import com.example.datltph21763_mob2041_code.DAO.ThanhVienDAO;
import com.example.datltph21763_mob2041_code.Model.PhieuMuon;
import com.example.datltph21763_mob2041_code.Model.Sach;
import com.example.datltph21763_mob2041_code.Model.ThanhVien;
import com.example.datltph21763_mob2041_code.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancrl;

    ThanhVienSpinerAdapter thanhVienSpinerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinerAdapter sachSpinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fabPm);
        dao = new PhieuMuonDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);//update
                return false;
            }
        });

        return v;
    }

    void capNhatLV() {
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTv);
        spSach = dialog.findViewById(R.id.spMaS);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThuePM);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancrl = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
//setNgayThue
        tvNgay.setText("Ngày thuê: " + sdf.format(new Date()));
        edMaPM.setEnabled(false);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinerAdapter = new ThanhVienSpinerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinerAdapter = new SachSpinerAdapter(context, listSach);
        spSach.setAdapter(sachSpinerAdapter);

        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền Thuê: " + tienThue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //edit set data len form
        if (type != 0) {
            edMaPM.setText(String.valueOf(item.getMaPN()));
            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaTV() == (listThanhVien.get(i).getMaTV())) {
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);
            for (int i = 0; i < listSach.size(); i++) {
                if (item.getMaSach() == (listSach.get(i).getMaSach())) {
                    positionTV = i;
                }
            }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: " + item.getTienThue());
            if (item.getTraSach() == 1) {
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }
        }
        btnCancrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()) {
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.setMaPN(Integer.parseInt(edMaPM.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLV();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(id);
                        capNhatLV();
                        dialog.cancel();
                    }
                }
        );
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