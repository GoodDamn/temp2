package good.damn.editor.appli.activities

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import good.damn.editor.appli.activities.auth.ALActivityLogin
import good.damn.editor.appli.activities.auth.ALActivitySignIn
import good.damn.editor.appli.extensions.focusActivity

class ALActivityEntry
: AppCompatActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        LinearLayout(
            context
        ).apply {

            orientation = LinearLayout
                .VERTICAL

            Button(
                context
            ).apply {

                text = "Войти"

                setOnClickListener {
                    focusActivity(
                        ALActivityLogin::class.java
                    )
                }

                addView(
                    this,
                    -1,
                    -2
                )
            }

            Button(
                context
            ).apply {
                text = "Зарегистрироваться"

                setOnClickListener {
                    focusActivity(
                        ALActivitySignIn::class.java
                    )
                }

                addView(
                    this,
                    -1,
                    -2
                )
            }

            setContentView(
                this
            )
        }

    }

}