fun radixSort(list: List<Int>): List<Int> {
    if (list.isEmpty()) return list

    var result = list
    var place = 1
    val maxValue = list.maxOrNull() ?: 0

    while (maxValue / place > 0) {
        result = sortByDigit(result, place)
        place *= 10
    }

    return result
}

private fun sortByDigit(list: List<Int>, place: Int): List<Int> {
    val count = IntArray(10) { 0 } 
    val output = MutableList(list.size) { 0 }

    for (num in list) {
        val digit = (num / place) % 10
        count[digit]++
    }

    for (i in 1 until 10) {
        count[i] += count[i - 1]
    }

    for (i in list.lastIndex downTo 0) {
        val num = list[i]
        val digit = (num / place) % 10
        output[count[digit] - 1] = num
        count[digit]--
    }

    return output
}
