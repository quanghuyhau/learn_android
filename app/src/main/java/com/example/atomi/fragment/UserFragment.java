package com.example.atomi.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atomi.R;
import com.example.atomi.activity.DataHome;
import com.example.atomi.activity.LoginActivity;
import com.example.atomi.activity.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView buttonLogOut;

    private FirebaseAuth Auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);


        recyclerView = view.findViewById(R.id.recycleView_fra);
        buttonLogOut = view.findViewById(R.id.button_logout_fra);



        Auth = FirebaseAuth.getInstance();
        TextView logoutButton = view.findViewById(R.id.button_logout_fra);

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

        MyAdapter myAdapter = new MyAdapter(dataHomes);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                getActivity().finish();
            }
        });

        return view;
    }
}
