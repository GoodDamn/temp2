package good.damn.editor.appli.repo.listener

import good.damn.editor.appli.models.ALModelEvent

interface ALListenerOnGetEvents {
    suspend fun onGetEventsList(
        events: List<ALModelEvent>
    )
}