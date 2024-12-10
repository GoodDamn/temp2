package good.damn.editor.appli.extensions

import android.content.Context
import android.widget.Toast

inline fun Context?.toast(
    msg: String
) = Toast.makeText(
    this,
    msg,
    Toast.LENGTH_LONG
).show()