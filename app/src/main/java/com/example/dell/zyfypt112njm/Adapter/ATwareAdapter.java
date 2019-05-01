package com.example.dell.zyfypt112njm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.zyfypt112njm.Activity.ViewTwareActivity;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.TwareBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ATwareAdapter extends RecyclerView.Adapter{
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态加载布局
    private List<TwareBean> list;//保存要显示的数据

    //1自定义：构造方法，传进上下文
    public ATwareAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    //2自定义：获取数据
    public void setList(List<TwareBean> list)
    {
        this.list=list;
    }
    //3自定义：ViewHolder子类
    public class ViewHolder extends RecyclerView.ViewHolder
    {   private ImageView imageView;
        private TextView tvtitle,tvdescript,tvtime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageView2);
            tvtitle=(TextView) itemView.findViewById(R.id.textView2);
            tvdescript=(TextView) itemView.findViewById(R.id.textView3);
            tvtime=(TextView) itemView.findViewById(R.id.textView4);
        }
    }
    //4重写：生成Item的View
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }
    //5重写：给ViewHolder中的控件填充值，设置监听
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TwareBean twareBean=list.get(position);
        if(twareBean==null) {
            return;
        }
        final ViewHolder  viewHolder=(ViewHolder)holder;

        viewHolder.tvtitle.setText(twareBean.getName());
        viewHolder.tvdescript.setText("描述："+twareBean.getDescription());
        viewHolder.tvtime.setText(twareBean.getUpdate_time());
        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"
                        +twareBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前条目信息，如id
               // int id = list.get(position).getId();
               // Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();
                //实际中，点击后打开新窗口做其他操作
                Intent intent=new Intent(context, ViewTwareActivity.class);
                intent.putExtra("pdfattach",list.get(position).getPdfattach());//
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("resid",list.get(position).getId());
                intent.putExtra("userid",list.get(position).getUserid());
                context.startActivity(intent);

            }
        });
    }
    //共有多少条目
    @Override
    public int getItemCount() {
        if(list==null) {
            return 0;
        } else {
            return list.size();
        }
    }
}
