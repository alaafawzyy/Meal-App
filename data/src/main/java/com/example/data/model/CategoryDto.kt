package com.example.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Category
import com.google.gson.annotations.SerializedName
@Entity
data class CategoryDto(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    @SerializedName("idCategory")
    val idCategory: String?,
    @SerializedName("strCategory")
    val strCategory: String?,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String?,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String?
){
    fun toCategory():Category{
        return Category(id = 0,idCategory = idCategory, strCategory = strCategory,
            strCategoryDescription = strCategoryDescription, strCategoryThumb = strCategoryThumb)
    }
}