package com.touktw.github.base

import androidx.core.widget.ContentLoadingProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-13
 */

abstract class LoadableFragment : BaseFragment() {
    abstract fun progressBar(): ContentLoadingProgressBar?

    abstract suspend fun load()

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).launch {
            showProgress()
            load()
            hideProgress()
        }
    }

    private fun showProgress() {
        progressBar()?.show()
    }

    private fun hideProgress() {
        progressBar()?.hide()
    }
}