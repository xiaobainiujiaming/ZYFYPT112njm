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

import com.example.dell.zyfypt112njm.Activity.ViewTwareActivity;
import com.example.dell.zyfypt112njm.bean.TwareBean;

import com.example.dell.zyfypt112njm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TwareAdapter extends RecyclerView.Adapter {
    private List<TwareBean>list;
    private Context context;
    private LayoutInflater layoutInflater;
    //构造函数
   public TwareAdapter(Context context){
       this.context = context;
       layoutInflater = LayoutInflater.from(context);
   }
   //设置数据list
    public void setList(List<TwareBean> list){this.list=list;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        TwareBean twareBean = list.get(i);
        if (twareBean == null){
            return;
        }
        ViewHolder viewHolder1=(ViewHolder) viewHolder;
        viewHolder1.tvtitle1.setText(twareBean.getName());
        viewHolder1.tvdescript1.setText(twareBean.getDescription());
        viewHolder1.tvtime1.setText(twareBean.getUpdate_time());
        //条目中的监听摁扭
        viewHolder1.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();

            }
        });

        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"+twareBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder1.imageView);

        //条目点击监听事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String id =list.get(i).getId().toString();
               // Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ViewTwareActivity.class);
                intent.putExtra("pdfattach",list.get(i).getPdfattach());//
                intent.putExtra("name",list.get(i).getName());
                intent.putExtra("userid",list.get(i).getUserid());
                intent.putExtra("resid",list.get(i).getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (list==null)return 0;
        else
            return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
       TextView tvtitle1,tvdescript1,tvtime1;
       Button button1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView2);
            tvtitle1 = (TextView) itemView.findViewById(R.id.textView2);
            tvdescript1 = (TextView)itemView.findViewById(R.id.textView3);
            tvtime1=(TextView)itemView.findViewById(R.id.textView4);
            button1=(Button)itemView.findViewById(R.id.button2);


        }
    }
}
