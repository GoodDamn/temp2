package good.damn.editor.appli.activities.univers

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import good.damn.editor.appli.ALApp
import good.damn.editor.appli.extensions.toast
import good.damn.editor.appli.models.ALModelUniversity
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
        university: ALModelUniversity
    ) = layout(university)


    private inline fun layout(
        model: ALModelUniversity
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

                    text = model.desc

                    addView(
                        this,
                        -2,
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