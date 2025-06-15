package com.helios.fizzbuzz.service

import com.helios.fizzbuzz.model.FizzBuzzRequest
import com.helios.fizzbuzz.model.StatsResponse
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Service
class FizzBuzzService {
    
    private val requestStats = ConcurrentHashMap<FizzBuzzRequest, AtomicInteger>()

    fun fizzbuzz(request: FizzBuzzRequest): List<String> {
        // Increment counter for this request
        requestStats.computeIfAbsent(request) { AtomicInteger(0) }.incrementAndGet()
        
        return (1..request.limit).map { number ->
            when {
                number % request.int1 == 0 && number % request.int2 == 0 -> request.str1 + request.str2
                number % request.int1 == 0 -> request.str1
                number % request.int2 == 0 -> request.str2
                else -> number.toString()
            }
        }
    }

    fun getStatistics(): StatsResponse {
        val mostFrequent = requestStats.maxByOrNull { it.value.get() }
            ?: throw RuntimeException("No requests made yet")
        
        return StatsResponse(
            int1 = mostFrequent.key.int1,
            int2 = mostFrequent.key.int2,
            limit = mostFrequent.key.limit,
            str1 = mostFrequent.key.str1,
            str2 = mostFrequent.key.str2,
            count = mostFrequent.value.get()
        )
    }
}