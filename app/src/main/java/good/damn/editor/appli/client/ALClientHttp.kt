package good.damn.editor.appli.client

import good.damn.editor.appli.ALApp
import good.damn.editor.appli.enums.ALEnumRole
import good.damn.editor.appli.client.listener.ALListenerOnAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class ALClientHttp(
    private val scope: CoroutineScope
) {

    var onAuth: ALListenerOnAuth? = null

    private val mClient = OkHttpClient()

    fun login(
        username: String,
        password: String
    ) = scope.launch {
        processAuth(
            makeRequest(
                username,
                password,
                "login"
            )
        )
    }

    fun signIn(
        username: String,
        password: String
    ) = scope.launch {
        processAuth(
            makeRequest(
                username,
                password,
                "signin"
            )
        )
    }

    private suspend inline fun processAuth(
        response: Response
    ) = when (
        response.code
    ) {
        200 -> {
            val id = response.body
                ?.string()
                ?.toIntOrNull()

            if (id == null) {
                withContext(
                    Dispatchers.Main
                ) {
                    onAuth?.onAuthError(
                        "200: No Body or No id"
                    )
                }
            } else {
                withContext(
                    Dispatchers.Main
                ) {
                    onAuth?.onAuthSuccess(
                        id
                    )
                }
            }
        }

        400 -> {
            withContext(
                Dispatchers.Main
            ) {
                onAuth?.onAuthError(
                    "Error: 404"
                )
            }
        }
        else -> Unit
    }

    private inline fun makeRequest(
        username: String,
        password: String,
        url: String
    ) = mClient.newCall(
        Request.Builder()
            .url(
                "${ALApp.url}/$url"
            ).post(
                jsonUser(
                    username,
                    password
                ).toString().toRequestBody(
                    "application/json".toMediaType()
                )
            ).build()
    ).execute()

    private inline fun jsonUser(
        username: String,
        password: String
    ) = JSONObject().apply {
        put("username", username)
        put("password", password)
        put("role", ALEnumRole.APPLICANT.raw)
    }
}