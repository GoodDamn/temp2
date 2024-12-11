package good.damn.editor.appli.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toGregorianString
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.repo.eventinfo.ALListenerOnCreateForm
import good.damn.editor.appli.repo.eventinfo.ALListenerOnGetEventInfo
import good.damn.editor.appli.repo.events.ALRepoEvents
import good.damn.editor.appli.repo.listener.ALListenerOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ALActivityEvent
: AppCompatActivity(),
ALListenerOnError,
ALListenerOnGetEventInfo,
ALListenerOnCreateForm, View.OnClickListener {

    companion object {
        const val EXTRA_ID = "id"
    }

    private var mEventId = -1

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

        ALApp.repos.eventInfo.apply {
            onError = this@ALActivityEvent
            onGetEventInfo = this@ALActivityEvent
            onFormCreate = this@ALActivityEvent

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
        event: ALModelEvent
    ) = layout(
        event
    )

    override suspend fun onCreateForm() =
        toast("Запись успешна")

    override fun onClick(
        v: View
    ) {
        v.isEnabled = false

        ALApp.repos.eventInfo.createForm(
            mEventId
        )
    }


    private inline fun layout(
        model: ALModelEvent
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

                    text = model.name

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    text = model.date.toGregorianString()

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                TextView(
                    context
                ).apply {

                    text = model.desc

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                if (model.withRegister) {
                    Button(
                        context
                    ).apply {

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
    }
}