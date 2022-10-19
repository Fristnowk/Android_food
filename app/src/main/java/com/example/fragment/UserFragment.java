package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.data.AppData;
import com.example.xc.MainActivity;
import com.example.xc.R;

public class UserFragment extends Fragment {

    private String s1="";                                    //用于存放是否不要葱
    private String s2="";                                    //用于存放是否不要香菜
    private String s3="";                                    //用于存放是否不要蒜
    private String s4="";                                    //用于存放是否不要动物油
    private String s5="";                                    //用于存放是否不要醋
    private String s6="";                                    //用于存放辣味
    private AppData app;

    /**
     *  本方法用于创建Fragment布局
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View   view=inflater.inflate(R.layout.user_fragment,container,false);            //关联布局文件

        app= (AppData) getActivity().getApplication();      //获得全局类
        initView(view);
        initCheckBox(view);

        return view;
    }

    /**
     *  本方法用于显示页面时刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initUserBtnColor();
    }

    /**
     * 用于初始化用于入口控件
     * @param view 传入我的页面布局对象
     */
    private  void initView(View view){
                                                            //点击购物评价入口
        CardView evaluate=view.findViewById(R.id.evaluate);
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
                                                           //点击购买记录入口
        CardView record=view.findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
                                                          //点击收货地址入口
        CardView adress=view.findViewById(R.id.adress);
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
                                                         //点击联系客服入口
        CardView server=view.findViewById(R.id.server);
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 用于初始化选框控件
     * @param view 传入我的页面布局对象
     */
    private  void initCheckBox(final View view){
                                                                //点击不要葱多选框
        final CheckBox cb1=view.findViewById(R.id.checkBox1);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){                                         //如果选中，
                    s1="-"+cb1.getText().toString();           //就获得选框里的值
                }
                else{                                          //如果没选中
                    s1="";                                     //就赋值为空
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });
                                                               //点击不要香菜多选框
        final CheckBox cb2=view.findViewById(R.id.checkBox2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s2="-"+cb2.getText().toString();
                }
                else{
                    s2="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;
            }
        });
                                                             //点击不要蒜多选框
        final CheckBox cb3=view.findViewById(R.id.checkBox3);
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s3="-"+cb3.getText().toString();
                }
                else{
                    s3="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;
            }
        });
                                                           //点击不要动物油多选框
        final CheckBox cb4=view.findViewById(R.id.checkBox4);
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s4="-"+cb4.getText().toString();
                }
                else{
                    s4="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;
            }
        });
                                                         //点击不要醋多选框
        final CheckBox cb5=view.findViewById(R.id.checkBox5);
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s5="-"+cb5.getText().toString();
                }
                else{
                    s5="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;
            }
        });
                                                        //点击单选框组
        RadioGroup radioGroup=view.findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                                        //获得选中的单选框
                RadioButton radioButton=view.findViewById(i);
                s6="-"+radioButton.getText().toString();//获得选框里的数据
                app.countremarks=s1+s2+s3+s4+s5+s6;
            }
        });
    }
}
