package com.touktw.github.select

import androidx.navigation.NavDirections

/**
 * Created by taekim on 2020-03-13
 */

enum class Function(val functionName: String, val navDirection: NavDirections) {
    USER_INFO("User Info", SelectFragmentDirections.actionSelectFragmentToUserFragment()),
    REPOSITORIES("Repositories", SelectFragmentDirections.actionSelectFragmentToRepositoriesFragment()),
}