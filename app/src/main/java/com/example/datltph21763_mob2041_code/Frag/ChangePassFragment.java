package com.example.datltph21763_mob2041_code.Frag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.datltph21763_mob2041_code.DAO.ThuThuDAO;
import com.example.datltph21763_mob2041_code.Model.ThuThu;
import com.example.datltph21763_mob2041_code.R;
import com.google.android.material.textfield.TextInputEditText;


public class ChangePassFragment extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    public ChangePassFragment() {
        // Required empty public constructor
    }


    public static ChangePassFragment newInstance(String param1, String param2) {
        ChangePassFragment fragment = new ChangePassFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        dao = new ThuThuDAO(getActivity());
        edPass = v.findViewById(R.id.edPassChange);
        edPassOld = v.findViewById(R.id.edPassOld);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSavePass);
        btnCancel = v.findViewById(R.id.btnCancelPass);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edPassOld.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate() > 0) {
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    if (dao.update(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edRePass.setText("");
                        edPassOld.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
//    loi o dau b

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            //doc user, pass
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu cũ không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }
}