package com.example.atomi.models



data class MyCartModel(
    var documentId: String = "",
    var currentTime: String? = null,
    var currentDate: String? = null,
    var productName: String? = null,
    var productPrice: String? = null,
    var totalQuantity: String? = null,
    var totalPrice: Int? = null,
    var productImage: String? = null
)

