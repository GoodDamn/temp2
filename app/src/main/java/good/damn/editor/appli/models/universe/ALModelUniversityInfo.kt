package good.damn.editor.appli.models.universe

import good.damn.editor.appli.models.event.ALModelEvent

data class ALModelUniversityInfo(
    val model: ALModelUniversity,
    val events: List<ALModelEvent>
)