package good.damn.editor.appli.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun AppCompatActivity.focusActivity(
    t: Class<*>
) {
    startActivity(
        Intent(
            this,
            t
        )
    )
}