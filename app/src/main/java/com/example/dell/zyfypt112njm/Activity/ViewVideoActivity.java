package com.example.dell.zyfypt112njm.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dell.zyfypt112njm.Listener.AttentionListener;
import com.example.dell.zyfypt112njm.Listener.CollectListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.model.CollectModel;
import com.example.dell.zyfypt112njm.model.FocusModel;


public class ViewVideoActivity extends AppCompatActivity {

    private static final String TAG = "ViewVideoActivity";
    private VideoView videoView;
    private String path="";
    private String BASEURL="http://amicool.neusoft.edu.cn/";
    private ProgressDialog dialog;

    private Boolean flagcollect = false;//收藏标志
    private Boolean flagfocus = false;
    private Context context;
    private String resid;
    private String userid;

    private CollectModel collectmodel;//收藏model
    private FocusModel focusModel;

   //private SharedPreferences sp;//简单存储
    //private String sessionID;



    //收藏
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
                case "2": System.out.println("----关注成功");
                    flagfocus=true;
                    item.setTitle("取消关注");

                    break;
                case "1":System.out.println("----关注失败");
                    break;
                case "4":System.out.println("----取消关注成功");
                    flagfocus=false;
                    item.setTitle("关注");
                    break;
                case "3":System.out.println("----取消关注失败");
                    break;
                case "5":System.out.println("----已关注");
                    flagfocus=true;
                    item.setTitle("取消关注");
                    break;
                case "6":System.out.println("----未关注");
                    flagfocus=false;
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
        setContentView(R.layout.activity_view_video);
        context = this;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置屏幕方向为横向
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//透明
        videoView=(VideoView)findViewById(R.id.videoView);

        path=BASEURL+"Uploads/video/video/"+getIntent().getStringExtra("videopath");//获取视频全路径
        dialog=ProgressDialog.show(ViewVideoActivity.this, "视频加载中...", "请您稍候");//进度条

        Uri uri = Uri.parse(path);
        videoView.setMediaController(new MediaController(this));//媒体播放控制工具
        videoView.setVideoURI(uri);//设置视频路径
        videoView.setOnPreparedListener(new MyPlayerOnPreparedListener());//播放开始回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());//播放完成回调
        videoView.requestFocus();// 让VideoView获取焦点
        videoView.start();//开始播放
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //readSP();
        Log.d(TAG, "onCreateOptionsMenu: " + userid);
        getMenuInflater().inflate(R.menu.menu, menu);//加载菜单布局

        collectmodel=new CollectModel();//实例化对象
        focusModel = new FocusModel();//实例化对象

        userid = getIntent().getStringExtra("userid");//获取传递的资源用户id
        resid = getIntent().getStringExtra(" resid");//获取传递的资源用户id

        collectmodel.exist("video", resid, LoginActivity.sessionID, listener);//判断是否收藏
        focusModel.exists("userfocus", userid, LoginActivity.sessionID,attentionListener );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menucollect:
                if (flagcollect)//如果已收藏，则调用取消收藏
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect("video", resid, LoginActivity.sessionID, listener);
                } else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect("video", resid, LoginActivity.sessionID, listener);
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




    //    private void readSP() {
//        SessionID=sp.getString("sessionID",null);
//    }
    //自定义子类，监听视频准备好，消除加载对话框
    class MyPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.setBackgroundColor(Color.argb(0, 0, 255, 0));
            dialog.dismiss();
        }
    }
    //自定义子类，监听播放完成，显示完成
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( ViewVideoActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().show();
        }
    }
}
