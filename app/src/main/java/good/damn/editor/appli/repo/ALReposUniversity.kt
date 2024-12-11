package good.damn.editor.appli.repo

import good.damn.editor.appli.repo.universities.ALRepoUniversities
import good.damn.editor.appli.repo.university.ALRepoUniversity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient

class ALReposUniversity(
    client: OkHttpClient
) {
    val scope = CoroutineScope(
        Dispatchers.IO
    )

    val universities = ALRepoUniversities(
        scope,
        client
    )

    val university = ALRepoUniversity(
        scope,
        client
    )

}