package com.example.dell.zyfypt112njm.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.LoginBean;
import com.example.dell.zyfypt112njm.Listener.LoginListener;
import com.example.dell.zyfypt112njm.model.LoginModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_username,et_password;
    private Button btn_login,btn_reg;//假如没引进来button用
    public static String sessionID;//静态变量
    private Switch sw;//定义记载密码的控件
    private SharedPreferences sp;//存储简单的信息


    private LoginListener loginListener=new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {
            sessionID = loginBean.getSessionid();//从新获取一下sessionid
            if(sessionID!=null){
                saveSP();//登陆成功时，保存用户名和密码
                sessionID=loginBean.getSessionid();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);//跳转
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this, "login error", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };//内部类

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("login",MODE_PRIVATE);//名字（可以自己起），权限   ----获取对象
        inint();
        readSP();
    }
    private void inint(){
        et_username=(EditText) findViewById(R.id.editText);
        et_password=(EditText) findViewById(R.id.editText2);
        btn_login=(Button) findViewById(R.id.btnlogin);
        btn_reg=(Button) findViewById(R.id.btnregister);
        sw=(Switch) findViewById(R.id.switch1);//获取控件
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                String username=et_username.getText().toString();
                String password=et_password.getText().toString();
                if(username.equals("")){
                    Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
                }else {
                    LoginModel loginModel=new LoginModel();
                    loginModel.getLogin(username,password,loginListener);
                }
             break;
            case R.id.btnregister:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);//跳转
                startActivity(intent);
             break;
        }

    }
    //保存
    private void saveSP(){
        //获取即将要保存的信息
        String username=et_username.getText().toString();
        String pass=et_password.getText().toString();
        Boolean remeber=sw.isChecked();
        //通过编译器函数设置
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",username);
        editor.putString("pass",pass);
        editor.putBoolean("remeber",remeber);
        editor.putString("sessionID",sessionID);
        editor.commit();//提交，写入文件
    }
    //读入
    private void readSP(){
        String sname=sp.getString("name",null);
        String spass=sp.getString("pass",null);
        Boolean re=sp.getBoolean("remeber",false);
        sw.setChecked(re);
        if(re){
            et_username.setText(sname);
            et_password.setText(spass);
        }
    }
}











