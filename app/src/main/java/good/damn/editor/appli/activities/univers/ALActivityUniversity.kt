package good.damn.editor.appli.activities.univers

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.adapters.ALAdapterEvents
import good.damn.editor.appli.decorations.ALDecorationMargin
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.universe.ALModelUniversity
import good.damn.editor.appli.models.universe.ALModelUniversityInfo
import good.damn.editor.appli.repo.listener.ALListenerOnError
import good.damn.editor.appli.repo.university.ALListenerOnGetUniversityInfo

class ALActivityUniversity
: AppCompatActivity(),
ALListenerOnError,
ALListenerOnGetUniversityInfo {

    companion object {
        const val EXTRA_ID = "id"
    }

    private var mUniversityId = -1

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        mUniversityId = intent.getIntExtra(
            EXTRA_ID,
            -1
        )

        ALApp.reposUniversities.university.apply {
            onError = this@ALActivityUniversity
            onGetUniversityInfo = this@ALActivityUniversity

            getUniversityInfoAsync(
                mUniversityId
            )
        }

    }

    override suspend fun onError(
        msg: String
    ) = toast(msg)

    override suspend fun onGetUniversityInfo(
        university: ALModelUniversityInfo
    ) = layout(university)


    private inline fun layout(
        info: ALModelUniversityInfo
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

                    text = info.model.desc

                    addView(
                        this,
                        -2,
                        -2
                    )
                }

                RecyclerView(
                    context
                ).let {

                    it.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                    it.adapter = ALAdapterEvents(
                        info.events
                    )

                    it.addItemDecoration(
                        ALDecorationMargin(
                            (ALApp.width * 0.1f).toInt()
                        )
                    )

                    addView(
                        it,
                        -1,
                        -2
                    )
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