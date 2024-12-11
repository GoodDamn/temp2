package good.damn.editor.appli.holders

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.activities.ALActivityEvent
import good.damn.editor.appli.activities.ALActivityListEvents
import good.damn.editor.appli.extensions.focusActivity
import good.damn.editor.appli.extensions.focusActivityIntent
import good.damn.editor.appli.extensions.toGregorianString
import good.damn.editor.appli.models.ALModelEvent

class ALHolderEvent(
    private val textViewName: TextView,
    private val textViewDesc: TextView,
    private val textViewDate: TextView,
    view: View,
): RecyclerView.ViewHolder(
    view
), View.OnClickListener {

    var model: ALModelEvent? = null
        set(v) {
            field = v
            v?.apply {
                textViewName.text = name
                textViewDesc.text = desc
                textViewDate.text = date.toGregorianString()
            }
        }

    override fun onClick(
        v: View?
    ) {
        val id = model?.id
            ?: return

        (v?.context as? AppCompatActivity)?.focusActivityIntent(
            ALActivityEvent::class.java
        ) {
            putExtra(
                ALActivityEvent.EXTRA_ID,
                id
            )
        }
    }

    companion object {
        inline fun create(
            context: Context
        ) = CardView(
            context
        ).run {

            setCardBackgroundColor(
                0xffffffff.toInt()
            )

            layoutParams = ViewGroup.LayoutParams(
                (ALApp.width * 0.8f).toInt(),
                (ALApp.height * 0.4f).toInt()
            )

            radius = layoutParams.height * 0.2f

            var textViewName: TextView
            var textViewDesc: TextView
            var textViewDate: TextView

            LinearLayout(
                context
            ).let { content ->

                content.orientation = LinearLayout
                    .VERTICAL

                textViewName = TextView(
                    context
                ).apply {

                    typeface = Typeface.DEFAULT_BOLD

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        this@run.layoutParams.height * 0.1f
                    )

                    layoutParams = ViewGroup.LayoutParams(
                        -1,
                        (this@run.layoutParams.height * 0.1f).toInt()
                    )

                    content.addView(
                        this
                    )
                }

                textViewDesc = TextView(
                    context
                ).apply {

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        this@run.layoutParams.height * 0.05f
                    )

                    layoutParams = ViewGroup.LayoutParams(
                        -1,
                        (this@run.layoutParams.height * 0.8f).toInt()
                    )

                    content.addView(
                        this
                    )
                }

                textViewDate = TextView(
                    context
                ).apply {

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        this@run.layoutParams.height * 0.05f
                    )

                    layoutParams = ViewGroup.LayoutParams(
                        -1,
                        (this@run.layoutParams.height * 0.1f).toInt()
                    )

                    content.addView(
                        this
                    )
                }

                addView(
                    content
                )
            }

            val holder = ALHolderEvent(
                textViewName,
                textViewDesc,
                textViewDate,
                this
            )


            setOnClickListener(
                holder
            )

            return@run holder
        }
    }
}