package com.linkedin.billing.livisitor.services

import android.app.IntentService
import android.content.Intent

class DemoService : IntentService("DemoService") {

    override fun onHandleIntent(intent: Intent?) {
       while (true) {
           println("I'm a service")
           Thread.sleep(100000)
       }
    }

}