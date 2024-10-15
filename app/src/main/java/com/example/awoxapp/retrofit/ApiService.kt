package com.example.awoxapp.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET (value = "food-recipes/categories")
    fun fetchCategories(): Call<List<Categories>>

    @GET (value = "food-recipes")
    fun fetchRecipes(): Call<List<Recipes>>

    @GET (value = "food-recipes/details")
    fun fetchDetailedRecipes(): Call<List<DetailedRecipes>>
}