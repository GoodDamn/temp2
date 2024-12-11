package good.damn.editor.appli

import android.app.Application
import good.damn.editor.appli.enums.ALEnumRole
import good.damn.editor.appli.repo.ALReposEvent
import good.damn.editor.appli.repo.ALReposUniversity
import okhttp3.OkHttpClient

class ALApp
: Application() {

    companion object {
        const val url = "http://192.168.31.191:8000"
        var role = ALEnumRole.APPLICANT
        var userId = -1
        var width = 0
        var height = 0

        val client = OkHttpClient()

        val reposUniversities = ALReposUniversity(
            client
        )

        val reposEvents = ALReposEvent(
            client
        )
    }

    override fun onCreate() {
        super.onCreate()


        resources.displayMetrics.apply {
            width = widthPixels
            height = heightPixels
        }
    }
}