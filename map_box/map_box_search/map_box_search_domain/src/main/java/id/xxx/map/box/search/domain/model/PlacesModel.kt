package id.xxx.map.box.search.domain.model

import android.os.Parcelable
import id.xxx.base.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlacesModel(

    override val id: String,

    val name: String,

    val address: String,

    val latitude: Double,

    val longitude: Double

) : BaseModel<String>, Parcelable