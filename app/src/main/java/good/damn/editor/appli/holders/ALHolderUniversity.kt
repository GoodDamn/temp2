package good.damn.editor.appli.holders

import android.content.Context
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
import good.damn.editor.appli.activities.univers.ALActivityUniversity
import good.damn.editor.appli.extensions.focusActivityIntent
import good.damn.editor.appli.models.ALModelUniversity

class ALHolderUniversity(
    private val textViewName: TextView,
    private val textViewDesc: TextView,
    view: View,
): RecyclerView.ViewHolder(
    view
), View.OnClickListener {

    var model: ALModelUniversity? = null
        set(v) {
            field = v
            v?.apply {
                textViewName.text = name
                textViewDesc.text = desc
            }
        }

    override fun onClick(
        v: View?
    ) {
        val id = model?.id
            ?: return

        (v?.context as? AppCompatActivity)?.focusActivityIntent(
            ALActivityUniversity::class.java
        ) {
            putExtra(
                ALActivityUniversity.EXTRA_ID,
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
                -2
            )

            radius = layoutParams.width * 0.03f

            var textViewName: TextView
            var textViewDesc: TextView

            LinearLayout(
                context
            ).let { content ->

                content.orientation = LinearLayout
                    .VERTICAL

                content.gravity = Gravity.CENTER_HORIZONTAL

                textViewName = TextView(
                    context
                ).apply {

                    typeface = Typeface.DEFAULT_BOLD

                    gravity = Gravity.CENTER_HORIZONTAL

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        this@run.layoutParams.height * 0.1f
                    )

                    content.addView(
                        this,
                        -1,
                        -2
                    )
                }

                textViewDesc = TextView(
                    context
                ).apply {

                    gravity = Gravity.CENTER_HORIZONTAL

                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        this@run.layoutParams.height * 0.05f
                    )

                    content.addView(
                        this,
                        -1,
                        -2
                    )
                }

                addView(
                    content
                )
            }

            val holder = ALHolderUniversity(
                textViewName,
                textViewDesc,
                this
            )


            setOnClickListener(
                holder
            )

            return@run holder
        }
    }
}