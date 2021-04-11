package ua.kpi.compsys.IO8120

import java.time.LocalDateTime

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {

    val timeDate = TimeOP(LocalDateTime.now())
    val def = TimeOP()

    val manual1 = TimeOP(23u, 59u, 59u)
    val manual2 = TimeOP(12u, 0u, 1u)
    val manual3 = TimeOP(0u, 0u, 0u)
    val manual4 = TimeOP(0u, 0u, 1u)

    println(timeDate.stringTime()+" is "+ timeDate.stringTwelveHours())
    println(manual1.stringTime()+" is "+ manual1.stringTwelveHours())
    println(manual2.stringTime()+" is "+ manual2.stringTwelveHours())
    println(manual4.stringTime()+" is "+ manual4.stringTwelveHours())

    println()

    println("Addition of ")
    manual1.showTime()
    manual2.showTime()
    println("result is ${(manual1.addTime(manual2)).stringTime()}")

    val test1 = TimeOP()
    println("method for 2 values "+test1.addTwoTimes(manual1, manual2).stringTime())
    println(test1.stringTwelveHours())

    println()

    println("Subtraction of")
    manual3.showTime()
    manual4.showTime()
    println("result is ${(manual3.subTime(manual4)).stringTime()}")
    val test2 = TimeOP()
    println("method for 2 values "+test2.subTwoTimes(manual3, manual4).stringTime())
    println(test2.stringTwelveHours())

}

@ExperimentalUnsignedTypes
class TimeOP () {
    private var hh: UInt = 0u
    private var mm: UInt = 0u
    private var ss: UInt = 0u
    constructor(_hh: UInt, _mm: UInt, _ss: UInt) : this() {
        if ((_hh in 0u..23u) && (_mm in 0u..59u) and (_ss in 0u..59u)){
            hh = _hh
            mm = _mm
            ss = _ss
        }
        else{
            println("Incorrect values")
        }
    }
    constructor(_date:LocalDateTime) : this() {
        hh = _date.hour.toUInt()
        mm = _date.minute.toUInt()
        ss = _date.second.toUInt()
    }
    fun showTime(){
        println(String.format("%02d:%02d:%02d", this.hh.toInt(), this.mm.toInt(), this.ss.toInt()))
    }

    fun stringTime(): String{
        return String.format("%02d:%02d:%02d", this.hh.toInt(), this.mm.toInt(), this.ss.toInt())
    }

    fun stringTwelveHours() :String{
        var temp = this.hh
        return if (this.hh < 12u){
            if (this.hh == 0u){
                temp += 12u
            }
            (String.format("%02d:%02d:%02d AM", temp.toInt(), this.mm.toInt(), this.ss.toInt()))
        } else{
            if (this.hh > 12u){
                temp -= 12u
            }
            (String.format("%02d:%02d:%02d PM", temp.toInt(), this.mm.toInt(), this.ss.toInt()))
        }
    }

    fun addTime(secondVal: TimeOP): TimeOP{
        val resultTime = TimeOP()
        resultTime.ss += this.ss + secondVal.ss
        if (resultTime.ss >= 60u){
            resultTime.mm += 1u
            resultTime.ss -= 60u
        }
        resultTime.mm += this.mm + secondVal.mm
        if (resultTime.mm >= 60u){
            resultTime.hh += 1u
            resultTime.mm -= 60u
        }
        resultTime.hh += this.hh + secondVal.hh
        if (resultTime.hh >= 24u){
            resultTime.hh -= 24u
        }
        return resultTime
    }


    fun subTime(secondVal: TimeOP): TimeOP{
        val resultTime = TimeOP()
        resultTime.ss += 60u
        resultTime.mm += 60u
        resultTime.hh += 24u

        resultTime.ss += this.ss - secondVal.ss
        if (resultTime.ss < 60u){
            resultTime.mm -= 1u
        }
        else{
            resultTime.ss -= 60u
        }
        resultTime.mm += this.mm - secondVal.mm
        if (resultTime.mm < 60u){
            resultTime.hh -= 1u
        }
        else{
            resultTime.mm -= 60u
        }
        resultTime.hh += this.hh - secondVal.hh
        if (resultTime.hh >= 24u){
            resultTime.hh -=24u
        }
        return resultTime
    }

    fun addTwoTimes(firstVal: TimeOP, secondVal: TimeOP): TimeOP{
        val resultTime = TimeOP()
        resultTime.ss += firstVal.ss + secondVal.ss
        if (resultTime.ss >= 60u){
            resultTime.mm += 1u
            resultTime.ss -= 60u
        }
        resultTime.mm += firstVal.mm + secondVal.mm
        if (resultTime.mm >= 60u){
            resultTime.hh += 1u
            resultTime.mm -= 60u
        }
        resultTime.hh += firstVal.hh + secondVal.hh
        if (resultTime.hh >= 24u){
            resultTime.hh -= 24u
        }
        this.hh = resultTime.hh
        this.mm = resultTime.mm
        this.ss = resultTime.ss
        return resultTime
    }


    fun subTwoTimes(firstVal: TimeOP, secondVal: TimeOP): TimeOP{
        val resultTime = TimeOP()
        resultTime.ss += 60u
        resultTime.mm += 60u
        resultTime.hh += 24u

        resultTime.ss += firstVal.ss - secondVal.ss
        if (resultTime.ss < 60u){
            resultTime.mm -= 1u
        }
        else{
            resultTime.ss -= 60u
        }
        resultTime.mm += firstVal.mm - secondVal.mm
        if (resultTime.mm < 60u){
            resultTime.hh -= 1u
        }
        else{
            resultTime.mm -= 60u
        }
        resultTime.hh += firstVal.hh - secondVal.hh
        if (resultTime.hh >= 24u){
            resultTime.hh -=24u
        }
        this.hh = resultTime.hh
        this.mm = resultTime.mm
        this.ss = resultTime.ss
        return resultTime
    }
 }