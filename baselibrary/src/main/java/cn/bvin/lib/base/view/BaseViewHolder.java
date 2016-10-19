package cn.bvin.lib.base.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * See https://github.com/hongyangAndroid/baseAdapter/
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void hide(){
        itemView.setVisibility(View.GONE);
    }

    public void show(){
        itemView.setVisibility(View.VISIBLE);
    }

}
