package yuri.dyachenko.translation.impl.timer

import yuri.dyachenko.translation.model.timer.TimestampProvider

class TimestampProviderImpl : TimestampProvider {

    override fun getMilliseconds() = System.currentTimeMillis()
}