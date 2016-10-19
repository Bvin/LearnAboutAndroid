package cn.bvin.lib.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.bvin.lib.base.view.BaseViewHolder;


/**
 * Created by bvin on 2016/9/20.
 * 对应单ViewHolder的RecyclerAdapter
 */
public abstract class SimpleRecyclerAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<H> {

    protected List<T> mData;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public SimpleRecyclerAdapter(Context context, int layoutId, List<T> data) {
        if (context != null) {
            mInflater = LayoutInflater.from(context);
        }
        mData = data;
        mLayoutId = layoutId;
    }

    /**
     * SimpleRecyclerAdapter
     * <p>使用此构造，createViewHolder(View rootView)里的rootView参数将会是null.
     * @param data 数据源
     */
    public SimpleRecyclerAdapter(List<T> data) {
        this(null, -1, data);
    }

    @Override
    public H onCreateViewHolder(ViewGroup viewGroup, int position) {
        if (mLayoutId > 0 || mInflater != null) {
            return createViewHolder(mInflater.inflate(mLayoutId, viewGroup, false));
        } else {
            return createViewHolder(null);
        }
    }

    @Override
    public void onBindViewHolder(H h, int position) {
        bindView(h, position, mData != null ? mData.get(position) : null);
    }

    /**
     * 创建具体ViewHolder.
     * @param rootView adapter root view，如果构造方法没传layoutId和Context将会是null值
     * @return 具体ViewHolder.
     */
    public abstract H createViewHolder(View rootView);

    public abstract void bindView(H holder, int position, T t);

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }
}
