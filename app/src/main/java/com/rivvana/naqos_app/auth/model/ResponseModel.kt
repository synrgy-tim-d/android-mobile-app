package com.rivvana.naqos_app.auth.model

import com.rivvana.naqos_app.model.Produk

class ResponseModel {
    var code = 0
    lateinit var message: String
    var data : ArrayList<Produk> = ArrayList()
}