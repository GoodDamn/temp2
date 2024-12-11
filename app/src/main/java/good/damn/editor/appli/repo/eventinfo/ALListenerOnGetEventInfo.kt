package good.damn.editor.appli.repo.eventinfo

import good.damn.editor.appli.models.ALModelEvent

interface ALListenerOnGetEventInfo {
    suspend fun onGetEventInfo(
        event: ALModelEvent
    )
}