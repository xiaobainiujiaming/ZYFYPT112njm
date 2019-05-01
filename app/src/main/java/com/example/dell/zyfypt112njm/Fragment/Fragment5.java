package com.example.dell.zyfypt112njm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.zyfypt112njm.Activity.AttentActivity;
import com.example.dell.zyfypt112njm.Activity.CollectActivity;
import com.example.dell.zyfypt112njm.Activity.LoginActivity;
import com.example.dell.zyfypt112njm.R;

public class Fragment5 extends Fragment implements View.OnClickListener{
    private Button attention, collect, logout;
    private TextView t;
    public Fragment5() {
    }

    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment5, container, false);
    }

    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attention =view. findViewById(R.id.attention);
        collect = view.findViewById(R.id.collect);
        logout = view.findViewById(R.id.logout);
        attention.setOnClickListener(this);
        collect.setOnClickListener(this);
        logout.setOnClickListener(this);
        t=(TextView) view.findViewById(R.id.textView13);

//        ((TextView) view.findViewById(R.id.textView14)).setText("id: " + sharedPreferences.getString("id", ""));
//        ((TextView) view.findViewById(R.id.textView15)).setText("username: " + sharedPreferences.getString("username", ""));
//        ((TextView) view.findViewById(R.id.textView16)).setText("realname: " +  sharedPreferences.getString("realname", ""));
//        ((TextView) view.findViewById(R.id.textView17)).setText("rolename: " +  sharedPreferences.getString("rolename", ""));
//        ((TextView) view.findViewById(R.id.textView9)).setText("sex: " +  sharedPreferences.getString("sex", "未设置"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attention:
                startActivity(new Intent(getActivity(), AttentActivity.class));
               break;
            case R.id.collect:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.logout:

                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
