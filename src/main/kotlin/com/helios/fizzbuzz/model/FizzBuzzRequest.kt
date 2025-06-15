package com.helios.fizzbuzz.model

data class FizzBuzzRequest(
    val int1: Int,
    val int2: Int,
    val limit: Int,
    val str1: String,
    val str2: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FizzBuzzRequest) return false
        return int1 == other.int1 && int2 == other.int2 && 
               limit == other.limit && str1 == other.str1 && str2 == other.str2
    }

    override fun hashCode(): Int {
        return listOf(int1, int2, limit, str1, str2).hashCode()
    }
}