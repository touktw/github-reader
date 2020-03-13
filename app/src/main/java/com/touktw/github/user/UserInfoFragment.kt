package com.touktw.github.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import com.touktw.github.R
import com.touktw.github.base.LoadableFragment
import com.touktw.github.model.UserModel
import com.touktw.github.net.GithubService
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * Created by taekim on 2020-03-13
 */

class UserInfoFragment : LoadableFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun progressBar(): ContentLoadingProgressBar? {
        return progressBar
    }

    override suspend fun load() {
        CoroutineScope(Dispatchers.Main).async {
            val response = withContext(Dispatchers.Default) {
                GithubService.get()
                        .getUser()
                        .execute()
            }

            when {
                response.isSuccessful -> {
                    response.body()?.let {
                        setUserInfo(it)
                    }
                }
                else -> {
                    Toast.makeText(context, "failed ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                }
            }
        }.await()
    }

    private fun setUserInfo(user: UserModel) {
        textUserInfo.text = StringBuilder()
                .append("name :")
                .append("\t")
                .append(user.name)
                .append("\n")
                .append("email :")
                .append("\t")
                .append(user.email)
                .append("\n")
                .append("url :")
                .append("\t")
                .append(user.htmlUrl)
    }
}