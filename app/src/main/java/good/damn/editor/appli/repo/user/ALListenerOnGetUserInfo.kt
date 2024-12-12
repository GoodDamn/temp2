package good.damn.editor.appli.repo.user

import good.damn.editor.appli.models.user.ALModelUserInfo

interface ALListenerOnGetUserInfo {
    suspend fun onGetUserInfo(
        info: ALModelUserInfo
    )
}