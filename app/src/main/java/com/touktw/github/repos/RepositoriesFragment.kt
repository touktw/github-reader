package com.touktw.github.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import com.touktw.github.R
import com.touktw.github.base.LoadableFragment
import com.touktw.github.net.GithubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * Created by taekim on 2020-03-13
 */

class RepositoriesFragment : LoadableFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun progressBar(): ContentLoadingProgressBar? {
        return null
    }

    override suspend fun load() {
        CoroutineScope(Dispatchers.Main).async {
            val response = withContext(Dispatchers.Default) {
                GithubService.get().getRepositories().execute()
            }

            if(response.isSuccessful) {

            }

            when {
                response.isSuccessful -> {
                }
                else -> {
                }
            }
        }.await()
    }
}