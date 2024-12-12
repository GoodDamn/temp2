package good.damn.editor.appli.repo

import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class ALRepoBase {
    var onError: ALListenerOnError? = null

    protected suspend inline fun error(
        msg: String
    ) {
        withContext(
            Dispatchers.Main
        ) {
            onError?.onError(
                "Error: $msg"
            )
        }
    }
}