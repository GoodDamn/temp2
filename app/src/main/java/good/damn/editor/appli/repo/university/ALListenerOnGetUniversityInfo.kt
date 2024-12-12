package good.damn.editor.appli.repo.university

import good.damn.editor.appli.models.universe.ALModelUniversity
import good.damn.editor.appli.models.universe.ALModelUniversityInfo

interface ALListenerOnGetUniversityInfo {
    suspend fun onGetUniversityInfo(
        university: ALModelUniversityInfo
    )
}