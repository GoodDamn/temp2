package good.damn.editor.appli.activities.events

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toGregorianString
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.event.ALModelEvent
import good.damn.editor.appli.models.event.ALModelEventInfo
import good.damn.editor.appli.repo.eventinfo.ALListenerOnCheckForm
import good.damn.editor.appli.repo.eventinfo.ALListenerOnCreateForm
import good.damn.editor.appli.repo.eventinfo.ALListenerOnGetEventInfo
import good.damn.editor.appli.repo.listener.ALListenerOnError

class ALActivityEvent
: AppCompatActivity(),
ALListenerOnError,
ALListenerOnGetEventInfo,
ALListenerOnCreateForm,
View.OnClickListener,
ALListenerOnCheckForm {

    companion object {
        const val EXTRA_ID = "id"
    }

    private var mEventId = -1

    private var mBtnRegister: Button? = null

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        mEventId = intent.getIntExtra(
            EXTRA_ID,
            -1
        )

        ALApp.reposEvents.eventInfo.apply {
            onError = this@ALActivityEvent
            onGetEventInfo = this@ALActivityEvent
            onFormCreate = this@ALActivityEvent
            onCheckForm = this@ALActivityEvent

            getEventInfoAsync(
                mEventId
            )
        }

    }

    override suspend fun onError(
        msg: String
    ) {
        toast(msg)
    }

    override suspend fun onGetEventInfo(
        event: ALModelEventInfo
    ) = layout(
        event
    )

    override suspend fun onCreateForm() =
        toast("Запись успешна")

    override suspend fun onCheckForm(
        exists: Boolean
    ) {
        mBtnRegister?.apply {
            isEnabled = !exists
            if (exists) {
                text = "Уже зарегистированы"
            }
        }
    }

    override fun onClick(
        v: View
    ) {
        v.isEnabled = false

        ALApp.reposEvents.eventInfo.createFormAsync(
            mEventId
        )
    }


    private inline fun layout(
        info: ALModelEventInfo
    ) {
        val context = this

        ScrollView(
            context
        ).let { scroll ->

            LinearLayout(
                context
            ).apply {
                orientation = LinearLayout
                    .VERTICAL

                TextView(
                    context
                ).apply {

                    text = info.model.name

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    text = "Дата: ${info.model.date.toGregorianString()}"

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    text = info.model.desc

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    typeface = Typeface.DEFAULT_BOLD

                    text = "Организатор:\n\n${info.university.name}"

                    addView(
                        this,
                        -1,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    text = info.university.desc

                    addView(
                        this,
                        -1,
                        -2
                    )
                }

                if (info.model.withRegister) {
                    mBtnRegister = Button(
                        context
                    ).apply {

                        isEnabled = false
                        text = "Зарегистрироваться"

                        setOnClickListener(
                            this@ALActivityEvent
                        )

                        addView(
                            this,
                            -1,
                            -2
                        )
                    }
                }

                scroll.addView(
                    this
                )
            }

            setContentView(
                scroll
            )
        }

        ALApp.reposEvents.eventInfo.checkFormAsync(
            mEventId
        )
    }
}