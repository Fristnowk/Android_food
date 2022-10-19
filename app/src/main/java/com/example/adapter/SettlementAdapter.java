package com.example.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.AppData;
import com.example.entity.Product;
import com.example.xc.R;

import java.util.List;

public class SettlementAdapter  extends RecyclerView.Adapter<SettlementAdapter.ViewHolder >{

    private FragmentActivity activity;
    private List<Product> list;
    private AppData app;
    private TextView  counttv;
    private TextView noproduct;
    private ScrollView scrollView;

    /**
     * @param activity 传入Activity对象
     * @param list 传入已选小吃数据集合
     */
    public SettlementAdapter(FragmentActivity activity, List<Product> list){
        this.activity=activity;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        app=(AppData) parent.getContext().getApplicationContext();        //获得全局类
        counttv= activity.findViewById(R.id.countPirce);                  //获得总价控件对象
        noproduct=activity.findViewById(R.id.noProduct);                  //获得没点菜控件对象
        scrollView=activity.findViewById(R.id.scrollView);                //获得ScrollView控件对象
                                                                          //关联列表选项布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settlement_item,parent,false);
        ViewHolder holder=new ViewHolder(view);                           //调用内部类ViewHolder

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        final Product product = list.get(position);                        //获得当前选项数据集合
                                                                          //异步加载选项数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((list.size() != 0)) {
                            holder.ivimage.setImageResource(product.getImage());
                            holder.tvname.setText(product.getName());
                            holder.tvnumber.setText(product.getNumber()+"");
                            holder.tvprice.setText(product.getPrice() + "元/份");
                        }
                    }
                });
            }
        }).start();
                                                                          //点击【+】按钮
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=product.getNumber()+1;                           //每点一次数量加1
                product.setNumber(num);                                  //设置当前数量
                holder.tvnumber.setText(num+"");                         //显示当前数量
                app.countprice +=Double.parseDouble(product.getPrice()); //添加当前价格到总价
                counttv.setText(app.countprice+"");                      //显示当前总价
            }
        });
                                                                        //点击【-】按钮
        holder.btnsubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getNumber()>1){                             //如果数量大于1
                    int num=product.getNumber()-1;                     //每点一次数量减1
                    product.setNumber(num);                            //设置当前数量
                    holder.tvnumber.setText(num+"");                   //显示当前数量
                                                                       //从总价里减去当前价格
                    app.countprice -=Double.parseDouble(product.getPrice());
                    counttv.setText(app.countprice+"");                //显示当前总价
                }
                else if(product.getNumber()==1){                       //如果数量等于1
                    int num=product.getNumber()-1;                     //每点一次数量减1
                    product.setNumber(num);                            //设置当前数量
                    app.selectedproduct.remove(position);              //从已选小吃集合里删除当前选项数据
                                                                       //从总价里减去当前价格
                    app.countprice -=Double.parseDouble(product.getPrice());
                    counttv.setText(app.countprice+"");                //显示当前总价
                    notifyDataSetChanged();                            //刷新列表数据
                }
                if(app.selectedproduct.size()==0){                     //如果已选小吃集合长度等于0
                    noproduct.setVisibility(View.VISIBLE);             //就显示没点菜界面
                    scrollView.setVisibility(View.GONE);               //就隐藏已点菜界面
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();                                          //获得选项数据集合长度
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivimage;
        TextView tvname;
        TextView tvprice;
        Button btnsubtract;
        TextView tvnumber;
        Button btnadd;
        EditText etremarks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivimage=itemView.findViewById(R.id.selectedImage);      //获得小吃图片控件对象
            tvname=itemView.findViewById(R.id.selectedName);        //获得小吃名称控件对象
            tvprice=itemView.findViewById(R.id.selectedPrice);      //获得小吃价格控件对象
            btnsubtract=itemView.findViewById(R.id.subtractButton); //获得【-】按钮控件对象
            tvnumber=itemView.findViewById(R.id.selectedNumber);    //获得小吃数量控件对象
            btnadd=itemView.findViewById(R.id.addButton);           //获得【+】按钮控件对象
            etremarks=itemView.findViewById(R.id.remarksText);      //获得小吃备注对象
        }
    }
}
