package com.kocci.healtikuy.ui.main.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.kocci.healtikuy.R

class HistoryAccordionAdapter(
    private val context: Context,
    private val groupList: List<String>,
    private val itemList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return groupList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return itemList[groupList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return itemList[groupList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var groupView = convertView
        var groupTitle = getGroup(groupPosition) as String
        if (groupView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            groupView = inflater.inflate(R.layout.group_list_history, null)
        }
        val tvGroupList = groupView!!.findViewById<TextView>(R.id.listTitle)
        tvGroupList.text = groupTitle
        return groupView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var childView = convertView
        var itemTitle = getChild(groupPosition, childPosition) as String
        if (childView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            childView = inflater.inflate(R.layout.item_list_history, null)
        }
        val tvChildList = childView!!.findViewById<TextView>(R.id.expandedListItem)
        tvChildList.text = itemTitle
        return childView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}