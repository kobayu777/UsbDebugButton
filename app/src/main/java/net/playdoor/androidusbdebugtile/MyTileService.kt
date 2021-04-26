package net.playdoor.androidusbdebugtile

import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class MyTileService : TileService() {
    override fun onStartListening() {
        if (qsTile != null) {
            qsTile.state = if (!isDevelopMode) Tile.STATE_UNAVAILABLE else if (isDebugMode) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            qsTile.updateTile()
        }
        super.onStartListening()
    }

    override fun onClick() {
        val toValue = if (isDebugMode) 0 else 1
        Settings.Global.putInt(contentResolver, Settings.Global.ADB_ENABLED, toValue)

        if (qsTile != null) {
            qsTile.state = if (toValue != 0) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            qsTile.updateTile()
        }
    }

    private val isDevelopMode: Boolean
        get() = Settings.Global.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
    private val isDebugMode: Boolean
        get() = Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) != 0
}
