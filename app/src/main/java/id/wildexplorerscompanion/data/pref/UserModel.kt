package id.wildexplorerscompanion.data.pref

data class UserModel(
    val userId: Int,
    val name: String,
    val email: String,
    val isLogin: Boolean = false
)