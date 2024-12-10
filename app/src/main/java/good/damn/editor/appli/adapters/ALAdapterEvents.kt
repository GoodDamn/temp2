package good.damn.editor.appli.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.holders.ALHolderEvent
import good.damn.editor.appli.models.ALModelEvent

class ALAdapterEvents(
    private val events: List<ALModelEvent>
): RecyclerView.Adapter<ALHolderEvent>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ALHolderEvent.create(
        parent.context
    )

    override fun getItemCount() =
        events.size

    override fun onBindViewHolder(
        holder: ALHolderEvent,
        position: Int
    ) {
        holder.textView.text = events[
            position
        ].name
    }
}