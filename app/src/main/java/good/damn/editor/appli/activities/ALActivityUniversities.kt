package good.damn.editor.appli.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterUniversities
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelUniversity
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.universities.ALListenerOnGetUniversities

class ALActivityUniversities
: AppCompatActivity(), ALListenerOnGetUniversities, ALListenerOnError {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        ALApp.reposUniversities.universities.apply {
            onGetUniversities = this@ALActivityUniversities
            onError = this@ALActivityUniversities

            getUniversitiesAsync()
        }
    }

    override suspend fun onGetUniversities(
        universities: List<ALModelUniversity>
    ) {
        val context = this
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

            adapter = ALAdapterUniversities(
                universities
            )

            setContentView(
                this
            )
        }
    }

    override suspend fun onError(
        msg: String
    ) {
        toast(msg)
    }

}