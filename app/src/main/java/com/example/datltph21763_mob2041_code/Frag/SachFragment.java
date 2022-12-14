package com.example.datltph21763_mob2041_code.Frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datltph21763_mob2041_code.Adapter.LoaiSachSpinerAdapter;
import com.example.datltph21763_mob2041_code.Adapter.SachAdapter;
import com.example.datltph21763_mob2041_code.DAO.LoaiSachDAO;
import com.example.datltph21763_mob2041_code.DAO.SachDAO;
import com.example.datltph21763_mob2041_code.Model.LoaiSach;
import com.example.datltph21763_mob2041_code.Model.Sach;
import com.example.datltph21763_mob2041_code.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SachFragment extends Fragment {
    ListView lvSach;
    SachDAO sachDAO;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;

    SachAdapter adapter;
    Sach item;
    List<Sach> list;

    LoaiSachSpinerAdapter spinerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
        capNhatLV();
        fab = v.findViewById(R.id.fabS);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(">>>>>>>>>>>", "onItemLongClick" );
                item = list.get(position);
                openDialog(getActivity(), 1);
                return true;
            }
        });
        return v;
    }

    void capNhatLV() {
        list = (List<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("B???n c?? mu???n x??a kh??ng");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                sachDAO.delete(Id);
                capNhatLV();
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

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaS);
        edTenSach = dialog.findViewById(R.id.edTenS);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelS);
        btnSave = dialog.findViewById(R.id.btnSaveS);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        spinerAdapter = new LoaiSachSpinerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinerAdapter);
        //lay maloaisach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach
                        .get(position).getMaLoai();
                Toast.makeText(context, "Ch???n"+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //kiem tra type 0- 1
        edMaSach.setEnabled(false);
        if (type != 0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "openDialog: " + position);
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if (validateForm() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Th??m th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(context, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLV();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public int validateForm() {
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "B???n ph???i nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}