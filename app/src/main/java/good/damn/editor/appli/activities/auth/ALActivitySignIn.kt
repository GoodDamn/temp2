package good.damn.editor.appli.activities.auth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.activities.ALActivityListEvents
import good.damn.editor.appli.extensions.focusActivity
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.client.ALClientHttp
import good.damn.editor.appli.client.listener.ALListenerOnAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ALActivitySignIn
: AppCompatActivity(), ALListenerOnAuth {

    private val mClient = ALClientHttp(
        CoroutineScope(
            Dispatchers.IO
        )
    ).apply {
        onAuth = this@ALActivitySignIn
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this

        LinearLayout(
            context
        ).apply {

            orientation = LinearLayout
                .VERTICAL

            val editTextLogin = EditText(
                context
            ).apply {

                hint = "Email"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            val editTextPassword = EditText(
                context
            ).apply {

                hint = "Пароль"

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
                    onClickBtnSignIn(
                        editTextLogin,
                        editTextPassword
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

    private inline fun onClickBtnSignIn(
        etUsername: EditText,
        etPassword: EditText
    ) {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username.isBlank() || password.isBlank()) {
            toast("Не все поля заполнены")
            return
        }

        mClient.signIn(
            username,
            password
        )
    }

    override suspend fun onAuthSuccess(
        id: Int
    ) {
        ALApp.userId = id
        focusActivity(
            ALActivityListEvents::class.java
        )
    }

    override suspend fun onAuthError(
        err: String
    ) = toast(err)

}