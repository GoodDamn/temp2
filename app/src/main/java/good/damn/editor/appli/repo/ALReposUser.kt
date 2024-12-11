package good.damn.editor.appli.repo

import good.damn.editor.appli.repo.user.ALRepoUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient

class ALReposUser(
    client: OkHttpClient
) {
    val scope = CoroutineScope(
        Dispatchers.IO
    )

    val user = ALRepoUser(
        scope,
        client
    )
}