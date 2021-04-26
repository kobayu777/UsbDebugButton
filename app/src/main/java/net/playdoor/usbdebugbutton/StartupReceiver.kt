package net.playdoor.usbdebugbutton

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class StartupReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        val startIntent = Intent(context, MyTileService::class.java)
        context.startService(startIntent)
    }
}