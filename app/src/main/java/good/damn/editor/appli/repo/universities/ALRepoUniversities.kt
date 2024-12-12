package good.damn.editor.appli.repo.universities

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toUniversitiesList
import good.damn.editor.appli.repo.ALRepoBase
import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class ALRepoUniversities(
    private val scope: CoroutineScope,
    private val client: OkHttpClient
): ALRepoBase() {
    companion object {
        const val URL = "${ALApp.url}/universities"
    }

    var onGetUniversities: ALListenerOnGetUniversities? = null

    fun getUniversitiesAsync() = scope.launch {
        val response = client.newCall(
            Request.Builder()
                .url(URL)
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

        val json = JSONArray(
            str
        ).toUniversitiesList()

        withContext(
            Dispatchers.Main
        ) {
            onGetUniversities?.onGetUniversities(
                json
            )
        }

    }

}