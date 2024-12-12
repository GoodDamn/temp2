package good.damn.editor.appli.models.event

import good.damn.editor.appli.models.universe.ALModelUniversity

data class ALModelEventInfo(
    val model: ALModelEvent,
    val university: ALModelUniversity
)