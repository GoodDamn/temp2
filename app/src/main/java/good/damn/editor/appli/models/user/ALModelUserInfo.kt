package good.damn.editor.appli.models.user

import org.json.JSONObject

data class ALModelUserInfo(
    val firstName: String,
    val secondName: String,
    val surname: String,
    val isMale: Boolean
) {
    inline fun toJson() = JSONObject().apply {
        put("firstName", firstName)
        put("secondName", secondName)
        put("surname", surname)
        put("isMale", isMale)
    }
}