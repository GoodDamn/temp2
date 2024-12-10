package good.damn.editor.appli.models

data class ALModelEvent(
    val id: Int,
    val name: String,
    val desc: String,
    val withRegister: Boolean,
    val date: Int
)