package com.example.atomi.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atomi.R;
import com.example.atomi.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private TextView buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        List<DataHome> dataHomes = new ArrayList<>();
        dataHomes.add(new DataHome(DataHome.TYPE_HEADER, 0, "Thông tin tài khoản", ""));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM, R.drawable.ic_home1, "Thông tin cá nhân", "Xem chi tiết và chỉnh sửa thông tin tài khoản cá nhân"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM, R.drawable.ic_home2, "Thông tin doanh nghiệp", "Xem chi tiết và chỉnh sửa thông tin về doanh nghiệp"));
        dataHomes.add(new DataHome(DataHome.TYPE_HEADER, 0, "Cài đặt chung", ""));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM, R.drawable.ic_home3, "Smart OTP", "Mã Smart OTP của tài khoản để thực hiện các thao tác phê duyệt yêu cầu"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM, R.drawable.ic_home4, "Đăng nhập bằng khuôn mặt", "Giúp cho việc đăng nhập của bạn dễ dàng và an toàn hơn"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home5,"Cài đặt mật khẩu","Thay đổi hoặc cài đặt mật khẩu để bảo vệ tài khoản của bạn"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home6,"Cài đặt ngôn ngữ","Cài đặt ngôn ngữ nhằm phù hợp với khu vực và người dùng"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home7,"Thông báo","Cài đặt hiển thị các thông báo để không bỏ lỡ các thông tin quan trọng"));
        dataHomes.add(new DataHome(DataHome.TYPE_HEADER, 0, "Hỗ trợ", ""));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home8,"Hướng dẫn sử dụng","Hướng dẫn người dùng sử dụng ứng dụng và trải nghiệm các dịch vụ của Olympus"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home9,"Trung tâm hỗ trợ","Trả lời một số thắc mắc của bạn trong quá trình sử dụng dịch vụ"));
        dataHomes.add(new DataHome(DataHome.TYPE_ITEM,R.drawable.ic_home10,"Đánh giá","Chúng tôi mong muốn được nhận những đánh giá của quý khách để cải thiện"));


        buttonLogOut = findViewById(R.id.button_logout1);
        recyclerView = findViewById(R.id.recycleView);

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "Bạn đã đăng xuất thành công");
            }
        });

        MyAdapter myAdapter = new MyAdapter(dataHomes);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
