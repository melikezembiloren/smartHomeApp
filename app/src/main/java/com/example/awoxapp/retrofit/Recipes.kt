package com.example.awoxapp.retrofit

data class Recipes(
    val aciklama: String,
    val dinlenmeSuresi: Int,
    val hazirlikSuresi: Int,
    val id: Int,
    val ipucu: String,
    val kategori: String,
    val ogunler: List<String>,
    val pisirmeSuresi: Int,
    val resimler: String,
    val servis: Int,
    val yemek: String,
    val zorluk: String
)