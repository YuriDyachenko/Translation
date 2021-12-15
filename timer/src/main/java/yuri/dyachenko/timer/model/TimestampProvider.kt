package yuri.dyachenko.timer.model

interface TimestampProvider {

    fun getMilliseconds(): Long
}