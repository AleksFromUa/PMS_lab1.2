package ua.kpi.compsys.IO8120

import kotlin.math.ceil

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun main() {
    val time = LocalTime.of(3, 15, 10)
    val timeDateTypeObject = LocalDateTime.now()


    println("The time is ${timeDateTypeObject.hour}:${timeDateTypeObject.minute}:${timeDateTypeObject.second}")


}
/*    // Частина 1

// Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."

    val studentsStr =
        "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82"

// Завдання 1
// Заповніть словник, де:
// - ключ – назва групи
// - значення – відсортований масив студентів, які відносяться до відповідної групи

    val studentsGroups = mutableMapOf<String, ArrayList<String>>()
// Ваш код починається тут

    val studentsList = studentsStr.split("; ")

    for (i in studentsList) {
        var pair = i.split(" - ")
        var group = pair[1]
        var emptyList = arrayListOf<String>()
        var tempList = studentsGroups.getOrElse(group) {emptyList}
        tempList.add(pair[0])
        studentsGroups.put(group, tempList)
    }
    for (i in studentsGroups.values) {
        i.sort()
    }


// Ваш код закінчується тут

    println("Завдання 1")
    for (i in studentsGroups){
        println(i)
    }
    println()

// Дано масив з максимально можливими оцінками

    val points = listOf(12, 12, 12, 12, 12, 12, 12, 16)

// Завдання 2
// Заповніть словник, де:
// - ключ – назва групи
// - значення – масив словників, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)

    fun randomValue(i: Int):Int{

        return when ((0..6).random()) {
            1 -> (ceil(i * 0.7)).toInt()
            2 -> (ceil(i * 0.9)).toInt()
            3, 4, 5 -> i
            else -> 0
        }
    }

    val studentPoints = mutableMapOf<String, ArrayList<Map<String, ArrayList<Int>>>>()

// Ваш код починається тут


    for (i in studentsGroups) {
        var tempStudentsPointsList = ArrayList<Map<String, ArrayList<Int>>>()
        for (j in i.value){
            var tempRandPoints = ArrayList<Int>()
            for (k in points){
                tempRandPoints.add(randomValue(k))
            }
            tempStudentsPointsList.add(mutableMapOf(j to tempRandPoints))
        }
        studentPoints.put(i.key, tempStudentsPointsList)
    }


// Ваш код закінчується тут

    println("Завдання 2")
    for (i in studentPoints){
        println(i)
    }
    println()

// Завдання 3
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – сума оцінок студента

    var sumPoints = mutableMapOf<String, ArrayList<Map<String, Int>>>()

// Ваш код починається тут

    for (group in studentPoints) {
        var tempStudentsPointsSumList = ArrayList<Map<String, Int>>()
        for (stud in group.value){
            var tempPointsSum = 0
            for (points in stud.values){
                tempPointsSum = points.sum()
            }
            for (i in stud.keys){
                tempStudentsPointsSumList.add(mutableMapOf(i to tempPointsSum))

            }
        }
        sumPoints.put(group.key, tempStudentsPointsSumList)
    }

// Ваш код закінчується тут

    println("Завдання 3")
    for (i in sumPoints){
        println(i)
    }
    println()


// Завдання 4
// Заповніть словник, де:
// - ключ – назва групи
// - значення – середня оцінка всіх студентів групи

    var averagePoints = mutableMapOf<String, Double>()

// Ваш код починається тут

    for (group in sumPoints){
        var tempSum = 0.0
        var counter = 0

        counter = group.value.size
        for (stud in group.value){
            tempSum += stud.values.sum()
        }
        tempSum /= group.value.size
        averagePoints.put(group.key, tempSum)
    }

// Ваш код закінчується тут

    println("Завдання 4")
    for (i in averagePoints){
        println(i)
    }
    println()


    // Завдання 5
// Заповніть словник, де:
// - ключ – назва групи
// - значення – масив студентів, які мають >= 60 балів

    var passedPerGroup = mutableMapOf<String, ArrayList<String>>()

// Ваш код починається тут

    for (group in sumPoints){
        var tempListStudents = ArrayList<String>()
        for (stud in group.value){
            for (i in stud){
                if (i.value >= 60){
                    tempListStudents.add(i.key)
                }
            }
        }
        passedPerGroup.put(group.key, tempListStudents)
    }

// Ваш код закінчується тут

    println("Завдання 5")
    for (i in passedPerGroup){
        println(i)
    }
    println()


// Приклад виведення. Ваш результат буде відрізнятися від прикладу через використання функції random для заповнення масиву оцінок та через інші вхідні дані.
//
//Завдання 1
//["ІВ-73": ["Гончар Юрій", "Давиденко Костянтин", "Капінус Артем", "Науменко Павло", "Чередніченко Владислав"], "ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-71": ["Андрющенко Данило", "Гуменюк Олександр", "Корнійчук Ольга", "Музика Олександр", "Трудов Антон", "Феофанов Іван"]]
//
//Завдання 2
//["ІВ-73": ["Давиденко Костянтин": [5, 8, 9, 12, 11, 12, 0, 0, 14], "Капінус Артем": [5, 8, 12, 12, 0, 12, 12, 12, 11], "Науменко Павло": [4, 8, 0, 12, 12, 11, 12, 12, 15], "Чередніченко Владислав": [5, 8, 12, 12, 11, 12, 12, 12, 15], "Гончар Юрій": [5, 6, 0, 12, 0, 11, 12, 11, 14]], "ІВ-71": ["Корнійчук Ольга": [0, 0, 12, 9, 11, 11, 9, 12, 15], "Музика Олександр": [5, 8, 12, 0, 11, 12, 0, 9, 15], "Гуменюк Олександр": [5, 8, 12, 9, 12, 12, 11, 12, 15], "Трудов Антон": [5, 0, 0, 11, 11, 0, 12, 12, 15], "Андрющенко Данило": [5, 6, 0, 12, 12, 12, 0, 9, 15], "Феофанов Іван": [5, 8, 12, 9, 12, 9, 11, 12, 14]], "ІВ-72": ["Киба Олег": [5, 8, 12, 12, 11, 12, 0, 0, 11], "Овчарова Юстіна": [5, 8, 12, 0, 11, 12, 12, 12, 15], "Бортнік Василь": [4, 8, 12, 12, 0, 12, 9, 12, 15], "Тимко Андрій": [0, 8, 11, 0, 12, 12, 9, 12, 15]]]
//
//Завдання 3
//["ІВ-72": ["Бортнік Василь": 84, "Тимко Андрій": 79, "Овчарова Юстіна": 87, "Киба Олег": 71], "ІВ-73": ["Капінус Артем": 84, "Науменко Павло": 86, "Чередніченко Владислав": 99, "Гончар Юрій": 71, "Давиденко Костянтин": 71], "ІВ-71": ["Корнійчук Ольга": 79, "Трудов Антон": 66, "Андрющенко Данило": 71, "Гуменюк Олександр": 96, "Феофанов Іван": 92, "Музика Олександр": 72]]
//
//Завдання 4
//["ІВ-71": 79.333336, "ІВ-72": 80.25, "ІВ-73": 82.2]
//
//Завдання 5
//["ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-73": ["Давиденко Костянтин", "Капінус Артем", "Чередніченко Владислав", "Гончар Юрій", "Науменко Павло"], "ІВ-71": ["Музика Олександр", "Трудов Антон", "Гуменюк Олександр", "Феофанов Іван", "Андрющенко Данило", "Корнійчук Ольга"]]
}

*/