package yuri.dyachenko.timer.impl

import yuri.dyachenko.timer.model.TimestampProvider

class TimestampProviderImpl : TimestampProvider {

    override fun getMilliseconds() = System.currentTimeMillis()
}