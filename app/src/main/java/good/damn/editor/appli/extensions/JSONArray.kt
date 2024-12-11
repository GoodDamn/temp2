package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.ALModelEvent
import org.json.JSONArray
import org.json.JSONObject

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