package com.example.common.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;

import com.example.common.R;
import com.example.common.databinding.ViewDialogLoadingBinding;

/**
 * Created by wyb on 2017/6/28.
 * 加载动画view
 * https://blog.csdn.net/shulianghan/article/details/105066654
 */
@SuppressLint("InflateParams")
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.loadingStyle);
        setContentView(ViewDialogLoadingBinding.inflate(getLayoutInflater()).getRoot(), new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void show(boolean flag) {
        setCancelable(flag);
        if (!isShowing()) {
            show();
        }
    }

    public void hide() {
        if (isShowing()) {
            dismiss();
        }
    }

}