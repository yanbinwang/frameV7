package com.example.common.widget.empty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.base.widget.SimpleViewGroup;
import com.example.common.R;
import com.example.common.widget.xrecyclerview.refresh.XRefreshLayout;
import com.example.common.widget.xrecyclerview.refresh.callback.OnXRefreshListener;

/**
 * Created by android on 2017/8/7.
 *
 * @author Wyb
 * <p>
 * 数据为空时候显示的页面（适用于列表，详情等）
 * 情况如下：
 * <p>
 * 1.加载中
 * 2.加载错误(只有断网情况下会显示点击刷新按钮)
 * 3.空布局(没有数据的时候显示)
 */
@SuppressLint("InflateParams")
public class EmptyLayout extends SimpleViewGroup {
    private View contextView;
    private XRefreshLayout xEmptyRefresh;//外层刷新
    private ImageView ivEmpty;//内容
    private TextView tvEmpty;//文本
    private OnEmptyRefreshListener onEmptyRefreshListener;
    private final String EMPTY_TXT = "没有数据";//数据为空时的内容
    private final String ERROR_TXT = "没有网络";//数据加载失败的内容

    public EmptyLayout(Context context) {
        super(context);
        initialize();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initialize();
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        Context context = getContext();
        contextView = LayoutInflater.from(context).inflate(R.layout.view_empty, null);
        xEmptyRefresh = contextView.findViewById(R.id.x_empty_refresh);
        ivEmpty = contextView.findViewById(R.id.iv_empty);
        tvEmpty = contextView.findViewById(R.id.tv_empty);
        xEmptyRefresh.setOnXRefreshListener(new OnXRefreshListener() {

            @Override
            public void onRefresh() {
                super.onRefresh();
                //进入加载中，并停止刷新动画
                showLoading();
                xEmptyRefresh.finishRefreshing();
                if (null != onEmptyRefreshListener) {
                    onEmptyRefreshListener.onRefreshListener();
                }
            }
        });
        contextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));//设置LayoutParams
        contextView.setOnClickListener(null);
        setBackgroundColor(ContextCompat.getColor(context, R.color.gray_f6f8ff));
        showLoading();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(contextView);
    }

    //设置列表所需的emptyview
    public View setListView(View listView) {
        removeView(contextView);
        ((ViewGroup) listView.getParent()).addView(contextView);//添加到当前的View hierarchy
        return contextView;
    }

    //当数据正在加载的时候显示（接口返回快速时会造成闪屏）
    public void showLoading() {
        xEmptyRefresh.setVisibility(View.GONE);
        ivEmpty.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
    }

    //当数据为空时(显示需要显示的图片，以及内容字)
    public void showEmpty() {
        showEmpty(-1, null);
    }

    //当数据为空时(显示需要显示的图片，以及内容字)---传入图片-1：原图 0：不需要图片 default：传入的图片
    public void showEmpty(int resId, String emptyText) {
        xEmptyRefresh.setVisibility(View.VISIBLE);
        ivEmpty.setBackgroundResource(0);
        if (-1 == resId) {
            ivEmpty.setVisibility(View.VISIBLE);
            ivEmpty.setImageResource(R.mipmap.img_data_empty);
        } else if (0 == resId) {
            ivEmpty.setVisibility(View.GONE);
        } else {
            ivEmpty.setVisibility(View.VISIBLE);
            ivEmpty.setImageResource(resId);
        }
        tvEmpty.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(emptyText)) {
            tvEmpty.setText(EMPTY_TXT);
        } else {
            tvEmpty.setText(emptyText);
        }
    }

    //当数据错误时（没有网络）
    public void showError() {
        xEmptyRefresh.setVisibility(View.VISIBLE);
        ivEmpty.setVisibility(View.VISIBLE);
        ivEmpty.setBackgroundResource(0);
        ivEmpty.setImageResource(R.mipmap.img_net_err);
        tvEmpty.setVisibility(View.VISIBLE);
        tvEmpty.setText(ERROR_TXT);
    }

    //设置背景颜色
    public void setBackgroundColor(int color) {
        xEmptyRefresh.setBackgroundColor(color);
    }

    //设置点击
    public void setOnEmptyRefreshListener(OnEmptyRefreshListener onEmptyRefreshListener) {
        this.onEmptyRefreshListener = onEmptyRefreshListener;
    }

}