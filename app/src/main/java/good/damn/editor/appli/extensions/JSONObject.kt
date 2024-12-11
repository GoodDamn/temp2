package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.models.ALModelUniversity
import good.damn.editor.appli.models.ALModelUserInfo
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

inline fun JSONObject.toUserInfoModel() = ALModelUserInfo(
    getString("firstName"),
    getString("secondName"),
    getString("surname"),
    try {
        getInt("isMale")
    } catch (e: Exception) {
        0
    } == 0
)

