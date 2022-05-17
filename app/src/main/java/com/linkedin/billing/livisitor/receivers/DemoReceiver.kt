package com.linkedin.billing.livisitor.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.linkedin.billing.livisitor.services.DemoService

class DemoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, DemoService::class.java))
    }
}