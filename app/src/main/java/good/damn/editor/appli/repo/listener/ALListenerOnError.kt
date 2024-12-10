package good.damn.editor.appli.repo.listener

interface ALListenerOnError {
    suspend fun onError(
        msg: String
    )
}