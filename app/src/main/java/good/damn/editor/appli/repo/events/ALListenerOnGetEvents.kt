package good.damn.editor.appli.repo.events

import good.damn.editor.appli.models.event.ALModelEvent

interface ALListenerOnGetEvents {
    suspend fun onGetEventsList(
        events: List<ALModelEvent>
    )
}