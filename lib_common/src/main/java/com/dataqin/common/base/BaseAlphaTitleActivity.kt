package com.dataqin.common.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.dataqin.common.databinding.ActivityAlphaBaseBinding
import com.dataqin.common.utils.builder.TitleBuilder

/**
 * Created by WangYanBin on 2020/6/10.
 * 带标题的基类，将整一个xml插入容器
 */
abstract class BaseAlphaTitleActivity<VB : ViewBinding> : BaseActivity<VB>() {
    private val baseBinding by lazy { ActivityAlphaBaseBinding.inflate(layoutInflater) }
    protected val titleBuilder by lazy { TitleBuilder(this, baseBinding.titleContainer) } //标题栏

    // <editor-fold defaultstate="collapsed" desc="基类方法">
    override fun setContentView(view: View?) {
        baseBinding.flBaseContainer.addView(binding.root)
        super.setContentView(baseBinding.root)
    }
    // </editor-fold>

}