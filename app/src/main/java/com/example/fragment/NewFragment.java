package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.adapter.NewAdapter;
import com.example.xc.MainActivity;
import com.example.xc.R;

public class NewFragment extends Fragment {
    /**
     *  本方法用于创建Fragment布局
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View   view=inflater.inflate(R.layout.new_fragment,container,false);            //关联布局文件
        initRecyclerView(view);
        return view;
    }

    /**
     *  本方法用于显示页面时刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initHomeBtnColor();
    }

    /**
     * 本方法用于实现RecyclerView+StaggeredGridLayout流式列表效果
     * @param view 传入新品布局对象
     */
    private void initRecyclerView(View view){

        RecyclerView rv=view.findViewById(R.id.newRecycler);                                        //获得RecyclerView控件对象
                                                                                                    //设置为StaggeredGridLayout流式布局
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL));
                                                                                                    //定义新品小吃图片数组
        int[] image={R.mipmap.bf11,R.mipmap.bf3,R.mipmap.bf1,
                R.mipmap.bf2,R.mipmap.bf7,R.mipmap.nf10,R.mipmap.nf8};
                                                                                                    //定义新品小吃说明数组
        String[] text={"豆汁","凉皮",
                "东北饺子","肉夹馍","羊肉串","叉烧包","招牌牛杂煲"};
        NewAdapter adapter=new NewAdapter(getActivity(),image,text);                                //调用适配器
        rv.setAdapter(adapter);                                                                     //设置适配器到RecyclerView
    }
}
