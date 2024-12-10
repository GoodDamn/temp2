package good.damn.editor.appli.holders

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ALHolderEvent(
    val textView: TextView
): RecyclerView.ViewHolder(
    textView
) {

    companion object {
        inline fun create(
            context: Context
        ) = TextView(
            context
        ).run {
            textSize = 15f

            gravity = Gravity
                .CENTER_HORIZONTAL

            return@run ALHolderEvent(
                this
            )
        }
    }
}