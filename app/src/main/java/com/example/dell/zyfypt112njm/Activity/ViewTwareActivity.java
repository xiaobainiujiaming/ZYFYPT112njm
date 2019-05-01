package com.example.dell.zyfypt112njm.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Listener.AttentionListener;
import com.example.dell.zyfypt112njm.Listener.CollectListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.callback.HttpCallBack;
import com.example.dell.zyfypt112njm.model.CollectModel;
import com.example.dell.zyfypt112njm.model.FocusModel;
import com.example.dell.zyfypt112njm.service.DownloadService;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewTwareActivity extends AppCompatActivity implements OnPageChangeListener{

    private String name="";
    private String attach="";
    private PDFView pdfView;
    private TextView tvinfo,tvpage;

    private String BASEURL ="http://amicool.neusoft.edu.cn/";

    private String resid;
    private String userid;//资源用户id
    private Context context;
    //private SharedPreferences sp;//简单存储
    //   private String sessionID="";  //sessionid
    private CollectModel collectmodel;//收藏model
    private FocusModel focusModel;
    private Boolean flagcollect=false;//收藏标志
    private Boolean flagfocus=false;//关注标志

    CollectListener listener = new CollectListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = findViewById(R.id.menucollect);

            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
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
        setContentView(R.layout.activity_view_tware);
        System.out.println("----查看课件详情");
        context = this;
        init();
        resid  = getIntent().getStringExtra("resid");//获取传递的资源id
        attach=getIntent().getStringExtra("pdfattach");
        name=getIntent().getStringExtra("name");
        System.out.println("----pdf地址："+attach);
        System.out.println("----pdf完整地址："+BASEURL+"/Uploads/"+attach);

        downloadfile();//下载文件


    }

    private void init() {
        tvinfo=(TextView)findViewById(R.id.textView10);
        tvpage=(TextView)findViewById(R.id.textView11);
        pdfView=(PDFView)findViewById(R.id.pdfview);
    }

    private void downloadfile() {
        String downloadUrl = "/Uploads/"+attach;    //补全pdf文件相对地址
        //定义Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//定义service
        DownloadService downloadService = retrofit.create(DownloadService.class);
//定义call
        Call<ResponseBody> responseBodyCall = downloadService.downloadFile(downloadUrl);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                System.out.println("----"+response.message()+" length "+response.body().contentLength()+" type "+response.body().contentType());
                //建立一个文件
                final File file = FileUtils4download.createFile(ViewTwareActivity.this,name);
                //下载文件放在子线程
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        FileUtils4download.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) {
                                /**更新进度条*/
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        tvinfo.setText(current+"");//当前进度
                                        System.out.println("----"+current+"--totale:"+total);

                                        if(current==total)  //如果达到最大值
                                        {
                                            tvinfo.setText("下载完成");
                                            tvinfo.setVisibility(View.GONE);//不可见
                                            String state = Environment.getExternalStorageState();
                                            String pdfName="";
                                            if(state.equals(Environment.MEDIA_MOUNTED)){
                                                pdfName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";
                                            }
                                            else pdfName=getCacheDir().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";

                                            display(pdfName, false);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage)
            setTitle(assetFileName);
        File file = new File(assetFileName);
        pdfView.fromFile(file)
                //.pages(0, 0, 0, 0, 0, 0) // 默认全部显示，pages属性可以过滤性显示
                .defaultPage(1)//默认展示第一页
                .onPageChange(this)//监听页面切换
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        tvpage.setText(page + "/" + pageCount);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //readSP();
        getMenuInflater().inflate(R.menu.menu, menu);//加载菜单布局
        collectmodel=new CollectModel();//实例化对象
        focusModel = new FocusModel();
        userid = getIntent().getStringExtra("userid");//获取传递的资源用户id
        collectmodel.exist("tware", resid, LoginActivity.sessionID, listener);//判断是否收藏
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
                    collectmodel.uncollect("tware", resid, LoginActivity.sessionID, listener);
                } else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect("tware", resid, LoginActivity.sessionID, listener);
                }
                break;
            case R.id.menufocus:
                if (flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备取消关注");
                    focusModel.unfocus("userfocus", userid, LoginActivity.sessionID, attentionListener);
                } else//如果未关注，则调用关注
                {
                    System.out.println("----准备关注");
                    focusModel.focus("userfocus", userid, LoginActivity.sessionID, attentionListener);
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


//    private void readSP() {
//        SessionID=sp.getString("sessionID",null);
//    }

}
