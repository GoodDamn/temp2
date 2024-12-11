package good.damn.editor.appli.repo.events

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toEventsList
import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class ALRepoEvents(
    private val scope: CoroutineScope
) {

    companion object {
        const val URL_EVENTS = "${ALApp.url}/events"
    }

    var onGetEvents: ALListenerOnGetEvents? = null

    var onError: ALListenerOnError? = null

    private val mClient = OkHttpClient()

    fun getEventsAsync() = scope.launch {

        val response = mClient.newCall(
            Request.Builder()
                .url(URL_EVENTS)
                .get()
                .build()
        ).execute()

        if (response.code == 200) {
            val str = response.body?.string()

            if (str == null) {
                error(
                    "200: No body"
                )
                return@launch
            }

            val events = JSONArray(
                str
            ).toEventsList()

            withContext(
                Dispatchers.Main
            ) {
                onGetEvents?.onGetEventsList(
                    events
                )
            }

            return@launch
        }

        error(
            "Error: ${response.code}"
        )
    }

    private suspend inline fun error(
        msg: String
    ) {
        withContext(
            Dispatchers.Main
        ) {
            onError?.onError(
                msg
            )
        }
    }

}