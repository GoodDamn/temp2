package good.damn.editor.appli

import android.app.Application
import good.damn.editor.appli.enums.ALEnumRole
import good.damn.editor.appli.repo.ALReposEvent

class ALApp
: Application() {

    companion object {
        const val url = "http://192.168.31.191:8000"
        var role = ALEnumRole.APPLICANT
        var userId = -1
        var width = 0
        var height = 0

        val repos = ALReposEvent()
    }

    override fun onCreate() {
        super.onCreate()


        resources.displayMetrics.apply {
            width = widthPixels
            height = heightPixels
        }
    }
}