package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.SettlementAdapter;
import com.example.data.AppData;
import com.example.xc.MainActivity;
import com.example.xc.R;

public class SettlementFragment extends Fragment {

    private AppData app;
    private RecyclerView rv;
    private TextView noproduct;
    private ScrollView scrollView;
    private TextView countprice;
    private EditText countremarks;
    private Button btnsettlement;

    /**
     *  本方法用于创建Fragment布局
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View   view=inflater.inflate(R.layout.settlement_fragment,container,false);            //关联布局文件

        app= (AppData) getActivity().getApplication();                      //获得全局类
        rv=view.findViewById(R.id.selectedRecycler);                        //获得列表控件对象
        noproduct=view.findViewById(R.id.noProduct);                        //获得没点菜时的控件对象
        scrollView=view.findViewById(R.id.scrollView);                      //获得ScrollView控件对象
        countprice=view.findViewById(R.id.countPirce);                      //获得总价控件对象
        countremarks=view.findViewById(R.id.countRemarks);                  //获得总备注控件对象
        btnsettlement=view.findViewById(R.id.settlement);                   //获得下单按钮对象

        return view;
    }

    /**
     *  本方法用于显示页面时刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initSettlementBtnColor();

        if(app.selectedproduct.size()!=0){                                 //如果已选小吃集合长度不等于0
            noproduct.setVisibility(View.GONE);                            //就隐藏没点菜控件
            scrollView.setVisibility(View.VISIBLE);                        //就显示已点菜控件
            initView();
        }
        else {                                                            //否则
            noproduct.setVisibility(View.VISIBLE);                        //就显示没点菜控件
            scrollView.setVisibility(View.GONE);                          //就隐藏已点菜控件
        }
    }

    /***
     * 本方法用于初始化页面数据
     */
    private  void initView(){
        countprice.setText(app.countprice+"");                           //显示已选小吃总价
        countremarks.setText(app.countremarks);                          //把总备注显示到控件里
                                                                         //设置列表为上下结构的LinearLayout布局
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
                                                                        //调用适配器
        SettlementAdapter adapter=new SettlementAdapter(getActivity(),app.selectedproduct);
        rv.setAdapter(adapter);                                         //把适配器设置给列表控件
                                                                        //点击下单按钮
        btnsettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                                       //提示框提示“暂时无法下单”
                Toast.makeText(getActivity(),"暂时无法下单",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
