package good.damn.editor.appli.repo

import good.damn.editor.appli.repo.eventinfo.ALRepoEventInfo
import good.damn.editor.appli.repo.events.ALRepoEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ALReposEvent {

    val scope = CoroutineScope(
        Dispatchers.IO
    )

    val events = ALRepoEvents(
        scope
    )

    val eventInfo = ALRepoEventInfo(
        scope
    )
}