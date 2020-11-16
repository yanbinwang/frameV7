package com.dataqin.common.widget.xrecyclerview.refresh;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.dataqin.common.R;
import com.dataqin.common.widget.xrecyclerview.refresh.callback.OnXRefreshBottomListener;
import com.dataqin.common.widget.xrecyclerview.refresh.callback.OnXRefreshListener;
import com.dataqin.common.widget.xrecyclerview.refresh.callback.OnXRefreshTopListener;

/**
 * Created by WangYanBin on 2020/9/17.
 * 刷新控件二次封装，设置对应项目的默认值
 */
public class XRefreshLayout extends SwipeRefreshLayout {
    private OnXRefreshTopListener onXRefreshTopListener;
    private OnXRefreshBottomListener onXRefreshBottomListener;
    private OnXRefreshListener onXRefreshListener;

    public XRefreshLayout(Context context) {
        super(context);
        initialize();
    }

    public XRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.black));
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                if (null != onXRefreshTopListener) onXRefreshTopListener.onRefresh();
                if (null != onXRefreshListener) onXRefreshListener.onRefresh();
            }

            @Override
            public void onLoad(int index) {
                if (null != onXRefreshBottomListener) onXRefreshBottomListener.onLoad();
                if (null != onXRefreshListener) onXRefreshListener.onLoad();
            }
        });
    }

    public void finishRefreshing() {
        setRefreshing(false);
    }

    public void setOnXRefreshTopListener(OnXRefreshTopListener onXRefreshTopListener) {
        this.onXRefreshTopListener = onXRefreshTopListener;
    }

    public void setOnXRefreshBottomListener(OnXRefreshBottomListener onXRefreshBottomListener) {
        this.onXRefreshBottomListener = onXRefreshBottomListener;
    }

    public void setOnXRefreshListener(OnXRefreshListener onXRefreshListener) {
        this.onXRefreshListener = onXRefreshListener;
    }

}