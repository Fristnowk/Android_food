package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ProductLeftAdapter;
import com.example.adapter.ProductRightAdapter;
import com.example.entity.Product;
import com.example.xc.MainActivity;
import com.example.xc.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    public static List<List<Product>> list=new ArrayList<>();          //定义存放右边列表所有选项数据的集合
    public static RecyclerView rightrv;

    /**
     *  本方法用于创建Fragment布局
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View   view=inflater.inflate(R.layout.products_fragment,container,false);       //关联布局文件
        initRecyclerView(view);
        return view;
    }

    /**
     *  本方法用于显示页面时刷新数据
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initProductBtnColor();
    }

    /***
     * 本方法用于实现左右列表功能逻辑核心步骤
     * @param view 传入点菜页面布局对象
     */
    private void initRecyclerView(View view){
        RecyclerView leftrv=view.findViewById(R.id.leftRecycler);      //获得左边列表控件对象
                                                                       //设置为上下结构的LinearLayout布局
        leftrv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
                                                                       //小吃分类名
        String[] classname={"南方小吃","北方小吃","原创小吃","亚洲小吃","欧美小吃"};
                                                                       //调用左边列表适配器类
        ProductLeftAdapter leftAdapter=new ProductLeftAdapter(getActivity(),classname);
        leftrv.setAdapter(leftAdapter);                                //把适配器设置给左边列表控件

                                                                       //定义5组小吃图片
        int[] nfimage={R.mipmap.nf1,R.mipmap.nf2,R.mipmap.nf3,R.mipmap.nf4,R.mipmap.nf5,R.mipmap.nf6,
                R.mipmap.nf7,R.mipmap.nf8,R.mipmap.nf9,R.mipmap.nf10,R.mipmap.nf11};
        int[] bfimage={R.mipmap.bf1,R.mipmap.bf2,R.mipmap.bf3,R.mipmap.bf4,R.mipmap.bf5,R.mipmap.bf6,
                R.mipmap.bf7,R.mipmap.bf8,R.mipmap.bf9,R.mipmap.bf10,R.mipmap.bf11};
        int[] ycimage={R.mipmap.yc1,R.mipmap.yc2,R.mipmap.yc3,R.mipmap.yc4,R.mipmap.yc5,R.mipmap.yc6,
                R.mipmap.yc7,R.mipmap.yc8,R.mipmap.yc9,R.mipmap.yc10};
        int[] yzimage={R.mipmap.yz1,R.mipmap.yz2,R.mipmap.yz3,R.mipmap.yz4,R.mipmap.yz5,R.mipmap.yz6,
                R.mipmap.yz7,R.mipmap.yz8,R.mipmap.yz9,R.mipmap.yz10,R.mipmap.yz11,};
        int[] omimage={R.mipmap.om1,R.mipmap.om2,R.mipmap.om3,R.mipmap.om4,R.mipmap.om5,
                R.mipmap.om6,R.mipmap.om7,R.mipmap.om8,R.mipmap.om9,R.mipmap.om10,};
                                                                       //定义5组小吃名称
        String[] nfname={"南方小吃1","南方小吃2","南方小吃3","南方小吃4","南方小吃5","南方小吃6",
                "南方小吃7","南方小吃8","南方小吃9","南方小吃10","南方小吃11"};
        String[] bfname={"北方小吃1","北方小吃2","北方小吃3","北方小吃4","北方小吃5","北方小吃6",
                "北方小吃7","北方小吃8","北方小吃9","北方小吃10","北方小吃11"};
        String[] ycname={"原创小吃1","原创小吃2","原创小吃3","原创小吃4","原创小吃5","原创小吃6",
                "原创小吃7","原创小吃8","原创小吃9","原创小吃10"};
        String[] yzname={"亚洲小吃1","亚洲小吃2","亚洲小吃3","亚洲小吃4","亚洲小吃5","亚洲小吃6",
                "亚洲小吃7","亚洲小吃8","亚洲小吃9","亚洲小吃10","亚洲小吃11"};
        String[] omname={"欧美小吃1","欧美小吃2","欧美小吃3","欧美小吃4","欧美小吃5","欧美小吃6",
                "欧美小吃7","欧美小吃8","欧美小吃9","欧美小吃10"};
                                                                      //定义5组小吃价格
        String[] nfprice={"4.5","5.5","6.5","3.0","4.0","5.0","6.0","2.0","7.0","7.5","8.0"};
        String[] bfprice={"4.5","5.5","6.5","3.0","4.0","5.0","6.0","2.0","7.0","7.5","8.0"};
        String[] ycprice={"4.5","5.5","6.5","3.0","4.0","5.0","6.0","2.0","7.0","7.5"};
        String[] yzprice={"4.5","5.5","6.5","3.0","4.0","5.0","6.0","2.0","7.0","7.5","8.0"};
        String[] omprice={"4.5","5.5","6.5","3.0","4.0","5.0","6.0","2.0","7.0","7.5"};
                                                                     //获取南方小吃分类数据，并添加的集合里。
        List<Product>  listright1=getList(nfimage, nfname, nfprice);
        list.add(listright1);
                                                                     //获取北方小吃分类数据，并添加的集合里。
        List<Product>  listright2=getList(bfimage, bfname, bfprice);
        list.add(listright2);
                                                                     //获取原创小吃分类数据，并添加的集合里。
        List<Product>  listright3=getList(ycimage, ycname, ycprice);
        list.add(listright3);
                                                                     //获取亚洲小吃分类数据，并添加的集合里。
        List<Product>  listright4=getList(yzimage, yzname, yzprice);
        list.add(listright4);
                                                                     //获取欧美小吃分类数据，并添加的集合里。
        List<Product>  listright5=getList(omimage, omname, omprice);
        list.add(listright5);

        rightrv=view.findViewById(R.id.rightRecycler);               //获得右边列表控件对象
                                                                     //设置为上下结构的LinearLayout布局
        rightrv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
                                                                     //调用右边列表适配器类
        ProductRightAdapter rightAdapter=new ProductRightAdapter(getActivity(),listright1);

        rightrv.setAdapter(rightAdapter);                            //把适配器设置给右边列表控件
    }

    /**
     * 本方法用于生成右边列表选项数据集合
     * @param iamges 传入一组小吃图片
     * @param names 传入一组小吃名称
     * @param prices 传入一组小吃价格
     * @return 返回一个分类的所有小吃数据集合
     */
    public List<Product> getList(int[] iamges, String[] names, String[] prices){
        List<Product>  listright=new ArrayList<>();                     //存放右边列表所有数据
        Product product;                                                //用于存放一个选项数据
        for(int i=0;i<iamges.length;i++){
            product=new Product();
            product.setImage(iamges[i]);
            product.setName(names[i]);
            product.setPrice(prices[i]);
            listright.add(product);
        }
        return listright;
    }

}
