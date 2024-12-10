package good.damn.editor.appli

import android.app.Application
import good.damn.editor.appli.enums.ALEnumRole

class ALApp
: Application() {

    companion object {
        const val url = "http://192.168.31.191:8000"
        var role = ALEnumRole.APPLICANT
        var userId = -1
    }

    override fun onCreate() {
        super.onCreate()
    }
}