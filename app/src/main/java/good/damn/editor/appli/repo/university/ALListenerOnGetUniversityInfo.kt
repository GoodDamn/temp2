package good.damn.editor.appli.repo.university

import good.damn.editor.appli.models.ALModelUniversity

interface ALListenerOnGetUniversityInfo {
    suspend fun onGetUniversityInfo(
        university: ALModelUniversity
    )
}