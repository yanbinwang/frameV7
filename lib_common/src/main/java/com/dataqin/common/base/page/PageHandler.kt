package com.dataqin.common.base.page

import android.text.TextUtils
import android.view.View
import com.dataqin.common.BaseApplication
import com.dataqin.common.R
import com.dataqin.common.utils.NetWorkUtil.isNetworkAvailable
import com.dataqin.common.widget.empty.EmptyLayout
import com.dataqin.common.widget.xrecyclerview.XRecyclerView
import com.dataqin.base.utils.ToastUtil.mackToastSHORT

/**
 * 遮罩层操作
 */
object PageHandler {

    @JvmStatic
    fun doResponse(msg: String?) {
        var str = msg
        val context = BaseApplication.instance?.applicationContext!!
        if (TextUtils.isEmpty(str)) {
            str = context.getString(R.string.label_response_err)
        }
        mackToastSHORT(if (!isNetworkAvailable()) context.getString(R.string.label_response_net_err) else str!!, context)
    }

    @JvmStatic
    fun setEmptyState(emptyLayout: EmptyLayout, msg: String?) {
        setEmptyState(emptyLayout, msg, -1, null)
    }

    @JvmStatic
    fun setEmptyState(emptyLayout: EmptyLayout, msg: String?, imgRes: Int, emptyText: String?) {
        doResponse(msg)
        emptyLayout.visibility = View.VISIBLE
        if (!isNetworkAvailable()) {
            emptyLayout.showError()
        } else {
            emptyLayout.showEmpty(imgRes, emptyText)
        }
    }

    @JvmStatic
    fun setListEmptyState(xRecyclerView: XRecyclerView, refresh: Boolean, msg: String?, length: Int) {
        setListEmptyState(xRecyclerView, refresh, msg, length, -1, null)
    }

    @JvmStatic
    fun setListEmptyState(xRecyclerView: XRecyclerView, refresh: Boolean, msg: String?, length: Int, imgRes: Int, emptyText: String?) {
        val emptyLayout = xRecyclerView.emptyView
        xRecyclerView.finishRefreshing()
        //区分此次刷新是否成功
        if (refresh) {
            emptyLayout.visibility = View.GONE
        } else {
            if (length > 0) {
                doResponse(msg)
                return
            }
            setEmptyState(emptyLayout, msg, imgRes, emptyText)
        }
    }

}