package good.damn.editor.appli.repo.user

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toUserInfoModel
import good.damn.editor.appli.models.ALModelUserInfo
import good.damn.editor.appli.repo.listener.ALListenerOnError
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
) {

    companion object {
        const val URL = "${ALApp.url}/user"
    }

    var onUpdateUserInfo: ALListenerOnUpdateUserInfo? = null
    var onGetUserInfo: ALListenerOnGetUserInfo? = null
    var onError: ALListenerOnError? = null

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

        withContext(
            Dispatchers.Main
        ) {
            onError?.onError(
                "Error: ${response.code} ${response.body?.string()}"
            )
        }
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
            withContext(
                Dispatchers.Main
            ) {
                onError?.onError(
                    "Error: No body"
                )
            }
            return@launch
        }

        if (response.code >= 400) {
            withContext(
                Dispatchers.Main
            ) {
                onError?.onError(
                    "Error: ${response.code} $str"
                )
            }
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