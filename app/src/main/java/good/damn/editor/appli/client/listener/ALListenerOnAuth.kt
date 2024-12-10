package good.damn.editor.appli.client.listener

interface ALListenerOnAuth {

    suspend fun onAuthSuccess(
        id: Int
    )

    suspend fun onAuthError(
        err: String
    )
}