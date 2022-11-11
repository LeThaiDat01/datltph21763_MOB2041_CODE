package com.example.datltph21763_mob2041_code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.datltph21763_mob2041_code.DAO.PhieuMuonDAO;
import com.example.datltph21763_mob2041_code.DAO.ThuThuDAO;
import com.example.datltph21763_mob2041_code.Database.DempDB;
import com.example.datltph21763_mob2041_code.Frag.ChangePassFragment;
import com.example.datltph21763_mob2041_code.Frag.DoanhThuFragment;
import com.example.datltph21763_mob2041_code.Frag.LoaiSachFragment;
import com.example.datltph21763_mob2041_code.Frag.PhieuMuonFragment;
import com.example.datltph21763_mob2041_code.Frag.SachFragment;
import com.example.datltph21763_mob2041_code.Frag.ThanhVienFragment;
import com.example.datltph21763_mob2041_code.Frag.TopFragment;
import com.example.datltph21763_mob2041_code.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nav = findViewById(R.id.nav_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigationdrawer_open, R.string.navigationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mHeaderView = nav.getHeaderView(0);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.framer, phieuMuonFragment).commit();

//        edUser = mHeaderView.findViewById(R.id.txtUser);
//        Intent i = getIntent();
//        String user = i.getStringExtra("user");
//        thuThuDAO = new ThuThuDAO(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String userName = thuThu.getHoTen();
//        edUser.setText("Welcome " + userName);
//        edUser.setTextColor(Color.BLACK);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lí phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.framer, phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lí Loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.framer, loaiSachFragment).commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lí Sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.framer, sachFragment).commit();
                        break;

                    case R.id.nav_ThanhVien:
                        setTitle("Quản lí Thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.framer, thanhVienFragment).commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction().replace(R.id.framer, topFragment).commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Quản lí Doanh Thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.framer, doanhThuFragment).commit();

                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thủ Thư");
                        break;
                    case R.id.sub_Pass:
                        setTitle("Đổi mật khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction().replace(R.id.framer, changePassFragment).commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}