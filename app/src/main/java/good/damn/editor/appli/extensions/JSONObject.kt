package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.models.ALModelUniversity
import org.json.JSONObject

inline fun JSONObject.toEventModel() = ALModelEvent(
    getInt("id"),
    getString("name"),
    getString("desc"),
    getInt("register").toBool(),
    getInt("date")
)

inline fun JSONObject.toUniversityModel() = ALModelUniversity(
    getInt("id"),
    getString("name"),
    getString("desc")
)

