package good.damn.editor.appli.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterEvents
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.repo.events.ALRepoEvents
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.events.ALListenerOnGetEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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

        ALApp.repos.events.apply {
            onGetEvents = this@ALActivityListEvents
            onError = this@ALActivityListEvents
            getEventsAsync()
        }

    }

    override suspend fun onGetEventsList(
        events: List<ALModelEvent>
    ) {
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
            setContentView(
                this
            )
        }
    }

    override suspend fun onError(
        msg: String
    ) = toast(msg)

}