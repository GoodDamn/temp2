package good.damn.editor.appli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterUniversities
import good.damn.editor.appli.decorations.ALDecorationMargin
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelUniversity
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.universities.ALListenerOnGetUniversities

class ALFragmentUniversities
: Fragment(), ALListenerOnGetUniversities, ALListenerOnError {

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

            setBackgroundColor(
                0xffffffff.toInt()
            )

            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            addItemDecoration(
                ALDecorationMargin(
                    (ALApp.width * 0.1f).toInt()
                )
            )

            mRecyclerView = this

        }

        ALApp.reposUniversities.universities.apply {
            onGetUniversities = this@ALFragmentUniversities
            onError = this@ALFragmentUniversities

            getUniversitiesAsync()
        }

        return mRecyclerView
    }

    override suspend fun onGetUniversities(
        universities: List<ALModelUniversity>
    ) {
        mRecyclerView?.adapter = ALAdapterUniversities(
            universities
        )
    }

    override suspend fun onError(
        msg: String
    ) = context?.toast(msg) ?: Unit

}