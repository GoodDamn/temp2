package good.damn.editor.appli.extensions

import good.damn.editor.appli.models.event.ALModelEvent
import good.damn.editor.appli.models.event.ALModelEventInfo
import good.damn.editor.appli.models.universe.ALModelUniversity
import good.damn.editor.appli.models.universe.ALModelUniversityInfo
import good.damn.editor.appli.models.user.ALModelUserInfo
import org.json.JSONObject

inline fun JSONObject.toEventModelInfo() = ALModelEventInfo(
    toEventModel(),
    getJSONObject(
        "university"
    ).toUniversityModel()
)

inline fun JSONObject.toEventModel() = ALModelEvent(
    getInt("id"),
    getString("name"),
    getString("desc"),
    getInt("register").toBool(),
    getInt("date")
)

inline fun JSONObject.toUniversityInfoModel() = ALModelUniversityInfo(
    toUniversityModel(),
    getJSONArray(
        "events"
    ).run {
        ArrayList<ALModelEvent>(
            length()
        ).apply {
            for (i in 0 until length()) {
                add(
                    getJSONObject(
                        i
                    ).toEventModel()
                )
            }
        }
    }
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

