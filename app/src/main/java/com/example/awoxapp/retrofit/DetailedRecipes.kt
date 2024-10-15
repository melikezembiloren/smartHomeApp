package com.example.awoxapp.retrofit

data class DetailedRecipes(
    val aciklama: String,
    val besleyiciDegerler: List<BesleyiciDegerler>,
    val dinlenmeSuresi: Int,
    val hazirlikSuresi: Int,
    val icindekiler: List<İcindekiler>,
    val id: Int,
    val ipucu: String,
    val kategori: String,
    val ogunler: List<String>,
    val pisirmeSuresi: Int,
    val resimler: String,
    val servis: Int,
    val yapimSureci: List<YapimSureci>,
    val yemek: String,
    val zorluk: String
)