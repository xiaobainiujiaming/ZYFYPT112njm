package com.example.dell.zyfypt112njm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.zyfypt112njm.Activity.ViewArticleActivity;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.ArticleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private List<ArticleBean> list;
    private Context context;
    private LayoutInflater layoutInflater;

    //构造函数
    public MyAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    public void setList(List<ArticleBean> list){
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ArticleBean article = list.get(i);
        if(article==null)  {
            return;
        }
        ViewHolder viewHolder1=(ViewHolder) viewHolder;
        viewHolder1.tvtitle.setText(article.getName());
        viewHolder1.tvdescript.setText(article.getDescription());
        viewHolder1.tvtime.setText(article.getUpdate_time());
        //条目点击按钮监听事件
        viewHolder1.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"+article.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder1.imageView);

        //条目点击事件
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // String strid=list.get(i).getId().toString();
               // Toast.makeText(context, strid, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ViewArticleActivity.class);
                intent.putExtra("resid",list.get(i).getId());
                intent.putExtra("userid",list.get(i).getUserid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list==null)
            return 0;
        else
        return list.size();
    }
    //定义ViewHolder子类，容纳item试图
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvtitle,tvdescript,tvtime;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageView2);
            tvtitle=(TextView) itemView.findViewById(R.id.textView2);
            tvdescript=(TextView) itemView.findViewById(R.id.textView3);
            tvtime=(TextView) itemView.findViewById(R.id.textView4);
            button=(Button) itemView.findViewById(R.id.button2);
        }

    }

}
