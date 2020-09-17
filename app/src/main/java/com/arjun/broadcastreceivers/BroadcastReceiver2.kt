package com.arjun.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import timber.log.Timber

class BroadcastReceiver2 : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = getResultExtras(true)
        var trail = bundle.getString(MainActivity.BREAD_CRUMB)
        trail = if (trail == null)
            "Start -> $TAG"
        else
            "$trail -> $TAG"

        bundle.putString(MainActivity.BREAD_CRUMB, trail)
        Timber.d(trail)
        Toast.makeText(context, "BroadcastReceiver2", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val TAG = BroadcastReceiver2::class.java.simpleName
    }

}