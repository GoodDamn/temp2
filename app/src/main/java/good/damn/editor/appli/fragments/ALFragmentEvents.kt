package good.damn.editor.appli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterEvents
import good.damn.editor.appli.decorations.ALDecorationMargin
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.event.ALModelEvent
import good.damn.editor.appli.repo.events.ALListenerOnGetEvents
import good.damn.editor.appli.repo.listener.ALListenerOnError

class ALFragmentEvents
: Fragment(),
ALListenerOnGetEvents,
ALListenerOnError {

    private var mRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
            ?: return null

        RecyclerView(
            context
        ).apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            setBackgroundColor(
                0xffffffff.toInt()
            )



            addItemDecoration(
                ALDecorationMargin(
                    (ALApp.width * 0.1f).toInt()
                )
            )

            mRecyclerView = this
        }

        ALApp.reposEvents.events.apply {
            onGetEvents = this@ALFragmentEvents
            onError = this@ALFragmentEvents
            getEventsAsync()
        }

        return mRecyclerView
    }

    override suspend fun onGetEventsList(
        events: List<ALModelEvent>
    ) {
        mRecyclerView?.adapter = ALAdapterEvents(
            events
        )
    }

    override suspend fun onError(
        msg: String
    ) = context?.toast(msg) ?: Unit

}