package com.dataqin.testnew.adapter

import android.text.TextUtils
import com.dataqin.common.base.binding.BaseQuickAdapter
import com.dataqin.common.base.binding.BaseViewBindingHolder
import com.dataqin.testnew.databinding.ItemTestBinding
import com.dataqin.testnew.model.SelectModel

/**
 * Created by WangYanBin on 2020/8/14.
 */
class TestAdapter(list: MutableList<SelectModel>) : BaseQuickAdapter<SelectModel, ItemTestBinding>(list) {
    private var maxLength = 0

    //设置需要加载的图片数量（默认0-第一条为展示）
    fun setItemCount(length: Int = 0) {
        maxLength = length
        val list = ArrayList<SelectModel>()
        for (i in 0..length) {
            list.add(SelectModel("", 0 == i))
        }
        data = list
        notifyDataSetChanged()
    }

    //设置要被"展示"的UI
    fun setItemShow(index: Int = 0, url: String = "") {
        if (index < data.size) {
            data[index].isShow = true
            data[index].url = url
            notifyDataSetChanged()
        }
    }

    //获取提交的集合
    fun getSubmitList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in data.indices) {
            val url = data[i].url
            if (!TextUtils.isEmpty(url)) {
                list.add(url!!)
            }
        }
        return list
    }

    override fun convert(holder: BaseViewBindingHolder, item: SelectModel?) {
        if (null != item) {
            holder.getBinding<ItemTestBinding>().apply {
                //先判断是否需要展示
                if (item.isShow) {
                    //再通过url判断是否需要加载默认图以及隐藏按钮
                    if (TextUtils.isEmpty(item.url)) {

                    } else {

                    }
                } else {

                }
//                val index = holder.adapterPosition
            }
        }
    }

    //设置要被"隐藏"的UI
    private fun setItemHide(index: Int = 0) {
        if (index < data.size) {
            data.removeAt(index)
            val list = ArrayList<SelectModel>()
            for (i in data.indices) {
                list.add(data[i])
            }
            if (list.size < maxLength) {
                for (i in list.size until maxLength) {
                    list.add(SelectModel("", 0 == i))
                }
            }
            //当前的长度,后一个是否展示
            val nowLength: Int = data.size
            if (nowLength < maxLength) {
                list[nowLength].isShow = true
            }
            data = list
            notifyDataSetChanged()
        }
    }

}