package com.example.dell.zyfypt112njm.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Listener.AttentionListener;
import com.example.dell.zyfypt112njm.Listener.CollectListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.common.Common;
import com.example.dell.zyfypt112njm.model.CollectModel;
import com.example.dell.zyfypt112njm.model.FocusModel;

public class ViewArticleActivity extends AppCompatActivity {

        private String resid;
        private WebView webView;
        private String userid;//资源用户id
        Context context;
        private static String stype;//定义收藏的类型
        private Boolean flagcollect=false;//收藏标志
        private Boolean flagfocus=false;//关注标志

    // private SharedPreferences sp;//简单存储
    //   private String sessionID="";  //sessionid
    // private String BASEURL="http://amicool.neusoft.edu.cn/";
        private CollectModel collectmodel;//收藏model
        private FocusModel focusModel;

    CollectListener listener=new CollectListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item=(ActionMenuItemView)findViewById(R.id.menucollect);
            //根据mode中response返回的字符串区分返回结果
            switch (msg)
            {
                case "2": System.out.println("----收藏成功");
                    flagcollect=true;
                    item.setTitle("取消收藏");

                    break;
                case "1":System.out.println("----收藏失败");
                    break;
                case "5":System.out.println("----取消收藏成功");
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                case "4":System.out.println("----取消收藏失败");
                    break;
                case "7":System.out.println("----已收藏");
                    flagcollect=true;
                    item.setTitle("取消收藏");
                    break;
                case "8":System.out.println("----未收藏");
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                default:
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };

    AttentionListener attentionListener = new AttentionListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = findViewById(R.id.menufocus);

            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
                case "2":
                    System.out.println("----关注成功");
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "1":
                    System.out.println("----关注失败");
                    break;
                case "4":
                    System.out.println("----取消关注成功");
                    flagfocus = false;
                    item.setTitle("关注");
                    break;
                case "3":
                    System.out.println("----取消关注失败");
                    break;
                case "5":
                    System.out.println("----已关注");
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "6":
                    System.out.println("----未关注");
                    flagfocus = false;
                    item.setTitle("关注");
                    break;
                default:
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_article);
            System.out.println("----查看文章详情");

            resid  = getIntent().getStringExtra("resid");//获取传递的资源id
            userid = getIntent().getStringExtra("userid");//获取传递的资源用户id

            Toast.makeText(this,resid,Toast.LENGTH_LONG)
                    .show();
            webView = (WebView)findViewById(R.id.webview);
           // webView.loadUrl(BASEURL+"article.php/show/index/id/"+resid);

            context=ViewArticleActivity.this;

            int type = getIntent().getIntExtra("type", 1);
            switch (type) {
                case 1:
                    stype = "article";
                    System.out.println("++++++++++++++++");
                    webView.loadUrl(Common.ARTICLEURL + resid);
                    break;
                case 2:
                    stype = "tcase";
                    webView.loadUrl(Common.TCASEURL + resid);
                    break;
                case 3:
                    stype = "project";
                    webView.loadUrl(Common.PROJECTURL + resid);
                    break;
                default:
                    break;
            }

            // sp=context.getSharedPreferences("login",MODE_PRIVATE);
            // readSP();//读取sessionid
        }
//    private void readSP() {
//        SessionID=sp.getString("sessionID",null);
//    }
    //菜单加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //readSP();
        getMenuInflater().inflate(R.menu.menu, menu);//加载菜单布局
        collectmodel=new CollectModel();//实例化对象
        focusModel = new FocusModel();
        resid  = getIntent().getStringExtra("resid");//获取传递的资源id
        userid = getIntent().getStringExtra("userid");//获取传递的资源用户id
        // collectmodel.collect("article",resid,LoginActivity.sessionID,listener);
        System.out.println(stype+"+++++++++++++++++");
        collectmodel.exist(stype, resid, LoginActivity.sessionID, listener);//判断是否收藏
        focusModel.exists("userfocus", userid, LoginActivity.sessionID,attentionListener );
        return true;
    }
//点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menucollect:
                //Toast.makeText(this, "文章收藏", Toast.LENGTH_SHORT).show();
                if(flagcollect)//如果已收藏，则调用取消收藏
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect(stype,resid,LoginActivity.sessionID,listener);
                    // collectmodel.uncollect("article", resid, LoginActivity.sessionID, listener);//判断是否收藏
                }
                else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect(stype,resid,LoginActivity.sessionID,listener);
                   //collectmodel.collect("article", resid, LoginActivity.sessionID, listener);
                }
                break;
            case R.id.menufocus:
                //Toast.makeText(this, "文章关注", Toast.LENGTH_SHORT).show();
                if(flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备关注");
                    focusModel.unfocus("userfocus", userid, LoginActivity.sessionID,attentionListener );
                }
                else//如果未关注，则调用关注
                {
                    System.out.println("----准备取消关注");
                    focusModel.focus("userfocus", userid, LoginActivity.sessionID,attentionListener );
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}