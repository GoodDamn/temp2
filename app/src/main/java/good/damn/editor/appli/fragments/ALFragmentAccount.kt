package good.damn.editor.appli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelUserInfo
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.user.ALListenerOnGetUserInfo
import good.damn.editor.appli.repo.user.ALListenerOnUpdateUserInfo

class ALFragmentAccount
: Fragment(),
ALListenerOnError,
ALListenerOnUpdateUserInfo,
ALListenerOnGetUserInfo {

    private var mEditTextFirstName: EditText? = null
    private var mEditTextSecondName: EditText? = null
    private var mEditTextSurname: EditText? = null

    private var mBtnSave: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
            ?: return null

        ALApp.reposUser.user.apply {
            onError = this@ALFragmentAccount
            onGetUserInfo = this@ALFragmentAccount
            onUpdateUserInfo = this@ALFragmentAccount

            getUserInfoAsync(
                ALApp.userId
            )
        }

        LinearLayout(
            context
        ).apply {
            orientation = LinearLayout
                .VERTICAL

            mEditTextSurname = EditText(
                context
            ).apply {
                hint = "Фамилия"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            mEditTextFirstName = EditText(
                context
            ).apply {
                hint = "Имя"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            mEditTextSecondName = EditText(
                context
            ).apply {
                hint = "Отчество"

                addView(
                    this,
                    -1,
                    -2
                )
            }

            mBtnSave = Button(
                context
            ).apply {
                text = "Сохранить"

                setOnClickListener {
                    onClickBtnSave(this)
                }

                addView(
                    this,
                    -1,
                    -2
                )
            }

            return this
        }
    }


    private inline fun onClickBtnSave(
        btn: Button
    ) {
        btn.isEnabled = false
        btn.text = "Обновление профиля..."
        ALApp.reposUser.user.updateUserInfoAsync(
            ALApp.userId,
            ALModelUserInfo(
                mEditTextFirstName?.text?.toString() ?: "",
                mEditTextSecondName?.text?.toString() ?: "",
                mEditTextSurname?.text?.toString() ?: "",
                true
            )
        )
    }

    override suspend fun onError(
        msg: String
    ) = context?.toast(msg) ?: Unit

    override suspend fun onUpdateUserInfo() {
        mBtnSave?.text = "Сохранить"
        mBtnSave?.isEnabled = true
        context?.toast("Профиль обновлён")
    }

    override suspend fun onGetUserInfo(
        info: ALModelUserInfo
    ) {
        mEditTextSurname?.setText(
            info.surname
        )

        mEditTextFirstName?.setText(
            info.firstName
        )

        mEditTextSecondName?.setText(
            info.secondName
        )
    }
}