package good.damn.editor.appli.repo.eventinfo

interface ALListenerOnCheckForm {
    suspend fun onCheckForm(
        exists: Boolean
    )
}