package good.damn.editor.appli.repo.events

import good.damn.editor.appli.models.ALModelEvent

interface ALListenerOnGetEvents {
    suspend fun onGetEventsList(
        events: List<ALModelEvent>
    )
}