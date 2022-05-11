package com.linkedin.billing.livisitor

class API {
    companion object {
        fun login(username: String, password: String,
                  callback: (Boolean)->Unit) {
            //TODO: go to a real DB or web service
            callback(
                username=="admin" && password=="1234"
            )
        }
    }
}

