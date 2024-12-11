package good.damn.editor.appli.repo.university

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toUniversityModel
import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ALRepoUniversity(
    private val scope: CoroutineScope,
    private val client: OkHttpClient
) {

    companion object {
        const val URL = "${ALApp.url}/university"
    }

    var onGetUniversityInfo: ALListenerOnGetUniversityInfo? = null
    var onError: ALListenerOnError? = null

    fun getUniversityInfoAsync(
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
                    "No body"
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
        ).toUniversityModel()

        withContext(
            Dispatchers.Main
        ) {
            onGetUniversityInfo?.onGetUniversityInfo(
                json
            )
        }

    }

}