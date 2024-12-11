package good.damn.editor.appli.extensions

import java.util.Calendar
import java.util.Date

inline fun Int.toBool() = this != 0

inline fun Int.toFormatString() =
    "${this / 10}${this%10}"

inline fun Int.toGregorianString(): String {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this * 1000L)

    return "${calendar.get(Calendar.DAY_OF_MONTH).toFormatString()}." +
        "${(calendar.get(Calendar.MONTH) + 1).toFormatString()}." +
        "${calendar.get(Calendar.YEAR)} " +
        "${calendar.get(Calendar.HOUR_OF_DAY).toFormatString()}:" +
        calendar.get(Calendar.MINUTE).toFormatString()
}