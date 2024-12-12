package good.damn.editor.appli.repo.universities

import good.damn.editor.appli.models.universe.ALModelUniversity

interface ALListenerOnGetUniversities {
    suspend fun onGetUniversities(
        universities: List<ALModelUniversity>
    )
}