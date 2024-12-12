package good.damn.editor.appli.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.holders.ALHolderUniversity
import good.damn.editor.appli.models.universe.ALModelUniversity

class ALAdapterUniversities(
    private val universities: List<ALModelUniversity>
): RecyclerView.Adapter<ALHolderUniversity>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ALHolderUniversity.create(
        parent.context
    )

    override fun getItemCount() =
        universities.size

    override fun onBindViewHolder(
        holder: ALHolderUniversity,
        position: Int
    ) = holder.run {
        model = universities[position]
    }


}