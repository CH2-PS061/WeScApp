package id.wildexplorerscompanion.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class DeleteResponse(

	@field:SerializedName("success")
	val success: String,

	@field:SerializedName("message")
	val message: String
)
