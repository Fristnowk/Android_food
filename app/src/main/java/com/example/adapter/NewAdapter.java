package com.example.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xc.R;


public  class NewAdapter  extends  RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private FragmentActivity activity;
    private int[] image;
    private String[] text;

    /***
     * @param activity 传入Activity对象
     * @param image 传入小吃图片
     * @param text 传入小吃文字说明
     */
    public NewAdapter(FragmentActivity activity, int[] image, String[] text){
        this.activity=activity;
        this.image=image;
        this.text=text;
    }

    /**
     *  用于创建选项布局
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //关联列表选项布局
        View view= LayoutInflater.from
                (parent.getContext()).inflate(R.layout.new_item,parent,false);
        ViewHolder holder=new ViewHolder(view);  //调用内部类ViewHolder

        return holder;
    }

    /**
     * 用于绑定选项数据
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
                                                            //异步加载选项数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if((image.length!=0)&&(text.length!=0)){
                            holder.iv.setImageResource(image[position]);
                            holder.tv.setText(text[position]);
                        }
                    }
                });
            }
        }).start();
    }

    /***
     *  用于设置选项数量
     */
    @Override
    public int getItemCount() {

        return image.length;                               //获得图片数组长度
    }

    /**
     * 内部类
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.newImage);                                                //获得列表选项图片控件
            tv=itemView.findViewById(R.id.newText);                                                 //获得列表选项文本控件
        }
    }
}
