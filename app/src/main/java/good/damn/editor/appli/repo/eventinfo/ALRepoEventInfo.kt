package good.damn.editor.appli.repo.eventinfo

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toEventModel
import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ALRepoEventInfo(
    private val scope: CoroutineScope
) {

    companion object {
        const val URL = "${ALApp.url}/event"
    }

    var onFormCreate: ALListenerOnCreateForm? = null
    var onGetEventInfo: ALListenerOnGetEventInfo? = null
    var onError: ALListenerOnError? = null

    private val mClient = OkHttpClient()

    fun createForm(
        eventId: Int
    ) = scope.launch {
        val response = mClient.newCall(
            Request.Builder()
                .url("$URL/$eventId/create")
                .build()
        ).execute()

        if (response.code >= 400) {
            withContext(
                Dispatchers.Main
            ) {
                onError?.onError(
                    "Error: ${response.code}"
                )
            }
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

        val response = mClient.newCall(
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
                    "200: No body"
                )
            }
            return@launch
        }

        if (response.code >= 400) {
            withContext(
                Dispatchers.Main
            ) {
                onError?.onError(
                    "Error: 400: $str"
                )
            }
            return@launch
        }

        val json = JSONObject(
            str
        ).toEventModel()

        withContext(
            Dispatchers.IO
        ) {
            onGetEventInfo?.onGetEventInfo(
                json
            )
        }
    }

}