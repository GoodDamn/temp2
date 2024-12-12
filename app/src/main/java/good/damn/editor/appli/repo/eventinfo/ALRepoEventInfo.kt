package good.damn.editor.appli.repo.eventinfo

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toEventModel
import good.damn.editor.appli.repo.ALRepoBase
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

class ALRepoEventInfo(
    private val scope: CoroutineScope,
    private val client: OkHttpClient
): ALRepoBase() {

    companion object {
        const val URL = "${ALApp.url}/event"
    }

    var onFormCreate: ALListenerOnCreateForm? = null
    var onGetEventInfo: ALListenerOnGetEventInfo? = null
    var onCheckForm: ALListenerOnCheckForm? = null

    fun checkFormAsync(
        eventId: Int
    ) = scope.launch {

        val response = client.newCall(
            Request.Builder()
                .url("$URL/$eventId/check/${ALApp.userId}")
                .get()
                .build()
        ).execute()


        val code = response.code
        withContext(
            Dispatchers.Main
        ) {
            onCheckForm?.onCheckForm(
                code == 200
            )
        }
    }

    fun createFormAsync(
        eventId: Int
    ) = scope.launch {
        val response = client.newCall(
            Request.Builder()
                .url("$URL/$eventId/create")
                .post(
                    "{ \"userId\": ${ALApp.userId}}".toRequestBody(
                        "application/json".toMediaType()
                    )
                )
                .build()
        ).execute()

        val str = response
            .body
            ?.string()

        if (str == null) {
            error(
                "No body"
            )
            return@launch
        }

        if (response.code >= 400) {
            error(
                "${response.code} $str"
            )
            return@launch
        }

        withContext(
            Dispatchers.Main
        ) {
            onFormCreate?.onCreateForm()
        }
    }

    fun getEventInfoAsync(
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
        ).toEventModel()

        withContext(
            Dispatchers.Main
        ) {
            onGetEventInfo?.onGetEventInfo(
                json
            )
        }
    }

}