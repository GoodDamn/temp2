package good.damn.editor.appli.repo

import good.damn.editor.appli.repo.eventinfo.ALRepoEventInfo
import good.damn.editor.appli.repo.events.ALRepoEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient

class ALReposEvent(
    client: OkHttpClient
) {

    val scope = CoroutineScope(
        Dispatchers.IO
    )

    val events = ALRepoEvents(
        scope,
        client
    )

    val eventInfo = ALRepoEventInfo(
        scope,
        client
    )
}