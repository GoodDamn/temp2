package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.ALModelEvent
import org.json.JSONArray
import org.json.JSONObject

inline fun JSONArray.toEventsList(): List<ALModelEvent> {
    val count = length()
    val list = ArrayList<ALModelEvent>(
        count
    )

    var obj: JSONObject
    for (i in 0 until count) {
        obj = getJSONObject(i)
        list.add(
            ALModelEvent(
                obj.getInt("id"),
                obj.getString("name"),
                obj.getString("desc"),
                obj.getInt("register").toBool(),
                obj.getInt("date")
            )
        )
    }

    return list
}