package yuri.dyachenko.translation.model.timer

interface TimestampProvider {

    fun getMilliseconds(): Long
}