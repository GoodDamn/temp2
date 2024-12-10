package good.damn.editor.appli.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.adapters.ALAdapterEvents
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelEvent
import good.damn.editor.appli.repo.ALRepoEvent
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.listener.ALListenerOnGetEvents
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

        ALRepoEvent(
            CoroutineScope(
                Dispatchers.IO
            )
        ).apply {
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