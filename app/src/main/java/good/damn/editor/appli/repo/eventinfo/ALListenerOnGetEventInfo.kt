package good.damn.editor.appli.repo.eventinfo

import good.damn.editor.appli.models.event.ALModelEventInfo

interface ALListenerOnGetEventInfo {
    suspend fun onGetEventInfo(
        event: ALModelEventInfo
    )
}