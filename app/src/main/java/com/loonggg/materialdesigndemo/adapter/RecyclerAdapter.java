package com.loonggg.materialdesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loonggg.materialdesigndemo.R;

/**
 * Created by loonggg on 2016/6/22.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public String[] datas = null;
    private OnItemClickListener mClickListener;

    public RecyclerAdapter(String[] datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item,
                viewGroup, false);
        final ViewHolder vh = new ViewHolder(view);
        if (mClickListener != null) {

            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(vh.itemView, vh.getLayoutPosition());
                }
            });
        }
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas[position]);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View itemView, int pos);
    }
}