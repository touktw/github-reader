package com.touktw.github.select

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.touktw.github.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_function_list.*

/**
 * Created by taekim on 2020-03-13
 */

class FunctionItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(item: Function) {
        button.text = item.functionName
        button.setOnClickListener {
            it.findNavController().navigate(item.navDirection)
        }
    }
}

class FunctionAdapter : RecyclerView.Adapter<FunctionItemViewHolder>() {
    val items = Function.values()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionItemViewHolder {
        return FunctionItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_function_list, null, false))
    }

    override fun onBindViewHolder(holder: FunctionItemViewHolder, position: Int) {
        items.getOrNull(position)?.let { function ->
            holder.bind(function)
        }
    }
}