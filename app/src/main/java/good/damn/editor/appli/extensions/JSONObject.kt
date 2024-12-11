package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.ALModelEvent
import org.json.JSONObject

inline fun JSONObject.toEventModel() = ALModelEvent(
    getInt("id"),
    getString("name"),
    getString("desc"),
    getInt("register").toBool(),
    getInt("date")
)