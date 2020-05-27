package com.example.share.widget.pop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.share.R;
import com.example.share.model.WeChatModel;
import com.example.share.utils.ShareSDKUtil;
import com.example.share.widget.pop.callback.OnSharePopListener;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;


/**
 * author: wyb
 * date: 2017/11/23.
 * 分享弹框
 */
@SuppressLint("InflateParams")
public class SharePop implements View.OnClickListener {
    private Context context;
    private View popView;
    private PopupWindow sharePop;
    private WeChatModel weChatModel;
    private OnSharePopListener onSharePopListener;

    public SharePop(Context context) {
        super();
        this.context = context;
    }

    public void showPop(View view) {
        if (popView == null) {
            popView = LayoutInflater.from(context).inflate(R.layout.view_pop_share, null);
            RelativeLayout shareContainerRel = popView.findViewById(R.id.share_container_rel);
            LinearLayout shareWechatLin = popView.findViewById(R.id.share_wechat_lin);
            LinearLayout shareWechatMomentsLin = popView.findViewById(R.id.share_wechat_moments_lin);
            shareContainerRel.setOnClickListener(this);
            shareWechatLin.setOnClickListener(this);
            shareWechatMomentsLin.setOnClickListener(this);
        }

        if (sharePop == null) {
            sharePop = new PopupWindow();
            sharePop.setContentView(popView);
            sharePop.setFocusable(true);
            sharePop.setOutsideTouchable(true);
            sharePop.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Slide slideIn = new Slide();
                slideIn.setDuration(500);
                //设置为进入
                slideIn.setMode(Visibility.MODE_IN);
                //设置从底部进入
                slideIn.setSlideEdge(Gravity.BOTTOM);
                sharePop.setEnterTransition(slideIn);

                Slide slideOut = new Slide();
                slideOut.setDuration(500);
                //设置为退出
                slideOut.setMode(Visibility.MODE_OUT);
                //设置从底部退出
                slideOut.setSlideEdge(Gravity.BOTTOM);
                sharePop.setExitTransition(slideOut);
            } else {
                sharePop.setAnimationStyle(R.style.pushBottomAnimStyle);
            }
            sharePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            sharePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            sharePop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        sharePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void hidePop() {
        if (sharePop != null) {
            sharePop.dismiss();
        }
    }

    public void setWeChatModel(WeChatModel weChatModel) {
        this.weChatModel = weChatModel;
    }

    public void setOnSharePopListener(OnSharePopListener onSharePopListener) {
        this.onSharePopListener = onSharePopListener;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.share_container_rel || viewId == R.id.share_cancel_tv) {
            if (null != onSharePopListener) {
                onSharePopListener.onShareCancel();
            }
            hidePop();
        } else if (viewId == R.id.share_wechat_lin) {
            weChatModel.setType(SendMessageToWX.Req.WXSceneSession);
            ShareSDKUtil.getInstance().shareWebPage(weChatModel);
            hidePop();
        } else if (viewId == R.id.share_wechat_moments_lin) {
            weChatModel.setType(SendMessageToWX.Req.WXSceneTimeline);
            ShareSDKUtil.getInstance().shareWebPage(weChatModel);
            hidePop();
        }
    }

}