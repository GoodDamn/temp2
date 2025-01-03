package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.event.ALModelEvent
import good.damn.editor.appli.models.universe.ALModelUniversity
import org.json.JSONArray

inline fun JSONArray.toEventsList(): List<ALModelEvent> {
    val count = length()
    val list = ArrayList<ALModelEvent>(
        count
    )

    for (i in 0 until count) {
        list.add(
            getJSONObject(
                i
            ).toEventModel()
        )
    }

    return list
}


inline fun JSONArray.toUniversitiesList(): List<ALModelUniversity> {
    val count = length()
    val list = ArrayList<ALModelUniversity>(
        count
    )

    for (i in 0 until count) {
        list.add(
            getJSONObject(
                i
            ).toUniversityModel()
        )
    }

    return list
}