package info.nightscout.implementation.queue.commands

import dagger.android.HasAndroidInjector
import info.nightscout.androidaps.interfaces.ActivePlugin
import info.nightscout.interfaces.Insight
import info.nightscout.interfaces.queue.Callback
import info.nightscout.androidaps.queue.commands.Command
import info.nightscout.implementation.R
import javax.inject.Inject

class CommandStopPump(
    injector: HasAndroidInjector,
    callback: Callback?
) : Command(injector, CommandType.STOP_PUMP, callback) {

    @Inject lateinit var activePlugin: ActivePlugin

    override fun execute() {
        val pump = activePlugin.activePump
        if (pump is Insight) {
            val result = pump.stopPump()
            callback?.result(result)?.run()
        }
    }

    override fun status(): String = rh.gs(R.string.stop_pump)

    override fun log(): String = "STOP PUMP"
}