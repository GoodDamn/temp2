package good.damn.editor.appli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class ALFragmentAccount
: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
            ?: return null

        LinearLayout(
            context
        ).apply {
            orientation = LinearLayout
                .VERTICAL

            EditText(
                context
            ).apply {
                hint = "Фамилия"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            EditText(
                context
            ).apply {
                hint = "Имя"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            EditText(
                context
            ).apply {
                hint = "Отчество"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            return this
        }
    }
}