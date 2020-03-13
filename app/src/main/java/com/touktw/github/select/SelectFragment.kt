package com.touktw.github.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.touktw.github.R
import com.touktw.github.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_select.*

/**
 * Created by taekim on 2020-03-13
 */

class SelectFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = FunctionAdapter()
    }
}