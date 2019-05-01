package com.example.dell.zyfypt112njm.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Listener.RegisterListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.model.RegisterModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

   private EditText etuser,etpass,etpass2,etanme,ettel;
   private Button bt1,bt2,bt3;
   // private ImageView imageView;

   private RegisterListener registerListener=new RegisterListener() {
       @Override
       public void onResponse(String registermgs) {
           System.out.println("------注册结果："+registermgs);
           if(registermgs.contains("1"))
           {
               Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
               finish();
           }
           else if(registermgs.contains("已存在"))
           {
               Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
           }
       }

       @Override
       public void onFail(String mgs) {
            System.out.println("------注册失败："+mgs);
           Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
       }
   };//内部类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inint();
    }
    public void  inint(){
        etuser=(EditText)findViewById(R.id.editText9);
        etpass=(EditText)findViewById(R.id.editText15);
        etpass2=(EditText)findViewById(R.id.editText11);
        etanme=(EditText)findViewById(R.id.editText13);
        ettel=(EditText)findViewById(R.id.editText18);
        bt1=(Button) findViewById(R.id.button);
        bt2=(Button) findViewById(R.id.button2);
        bt3=(Button) findViewById(R.id.button3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                String username=etuser.getText().toString();
                String password=etpass.getText().toString();
                String password_ok=etpass2.getText().toString();
                String realname=etanme.getText().toString();
                String tel=ettel.getText().toString();
                if(username.equals("")){
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }else if (password.equals("")){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if ( password_ok.equals("")){
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }else if (realname.equals("")){
                    Toast.makeText(this, "电话不能为空", Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(this, "密码不相同", Toast.LENGTH_SHORT).show();
                }else {
                    RegisterModel registerModel=new RegisterModel();
                    registerModel.getRegisterResult(username,password,tel,registerListener);
                }
            break;
            case R.id.button3:
            finish();
            break;
        }
    }
}
