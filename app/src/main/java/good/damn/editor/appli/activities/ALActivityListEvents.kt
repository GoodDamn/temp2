package good.damn.editor.appli.activities

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterEvents
import good.damn.editor.appli.extensions.focusActivity
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.events.ALListenerOnGetEvents

class ALActivityListEvents
: AppCompatActivity(),
    ALListenerOnGetEvents,
ALListenerOnError {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        ALApp.reposEvents.events.apply {
            onGetEvents = this@ALActivityListEvents
            onError = this@ALActivityListEvents
            getEventsAsync()
        }

    }

    override suspend fun onGetEventsList(
        events: List<ALModelEvent>
    ) {
        FrameLayout(
            this
        ).let { root ->

            RecyclerView(
                this
            ).apply {
                layoutManager = LinearLayoutManager(
                    this@ALActivityListEvents,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                setBackgroundColor(
                    0xffffffff.toInt()
                )

                adapter = ALAdapterEvents(
                    events
                )

                root.addView(
                    this
                )
            }

            Button(
                this
            ).apply {

                text = "Университеты"

                setOnClickListener {
                    focusActivity(
                        ALActivityUniversities::class.java
                    )
                }

                layoutParams = FrameLayout.LayoutParams(
                    -1,
                    -2
                ).apply {
                    gravity = Gravity.BOTTOM
                }

                root.addView(
                    this
                )
            }

            setContentView(
                root
            )
        }
    }

    override suspend fun onError(
        msg: String
    ) = toast(msg)

}