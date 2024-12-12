package good.damn.editor.appli.repo.user

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toUserInfoModel
import good.damn.editor.appli.models.user.ALModelUserInfo
import good.damn.editor.appli.repo.ALRepoBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ALRepoUser(
    private val scope: CoroutineScope,
    private val client: OkHttpClient
): ALRepoBase() {

    companion object {
        const val URL = "${ALApp.url}/user"
    }

    var onUpdateUserInfo: ALListenerOnUpdateUserInfo? = null
    var onGetUserInfo: ALListenerOnGetUserInfo? = null

    fun updateUserInfoAsync(
        id: Int,
        model: ALModelUserInfo
    ) = scope.launch {
        val response = client.newCall(
            Request.Builder()
                .put(
                    model.toJson().toString().toRequestBody(
                        "application/json".toMediaType()
                    )
                ).url("$URL/$id/update")
                .build()
        ).execute()

        if (response.code == 200) {
            withContext(
                Dispatchers.Main
            ) {
                onUpdateUserInfo?.onUpdateUserInfo()
            }
            return@launch
        }

        error(
            "${response.code} ${response.body?.string()}"
        )
    }

    fun getUserInfoAsync(
        id: Int
    ) = scope.launch {

        val response = client.newCall(
            Request.Builder()
                .url("$URL/$id")
                .get()
                .build()
        ).execute()

        val str = response
            .body
            ?.string()

        if (str == null) {
            error(
                "${response.code} No body"
            )
            return@launch
        }

        if (response.code >= 400) {
            error(
                "${response.code} $str"
            )
            return@launch
        }

        val json = JSONObject(
            str
        ).toUserInfoModel()

        withContext(
            Dispatchers.Main
        ) {
            onGetUserInfo?.onGetUserInfo(
                json
            )
        }

    }

}