package com.helios.fizzbuzz.controller

import com.helios.fizzbuzz.model.FizzBuzzRequest
import com.helios.fizzbuzz.model.StatsResponse
import com.helios.fizzbuzz.service.FizzBuzzService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class FizzBuzzController(private val fizzBuzzService: FizzBuzzService) {

    @GetMapping("/fizzbuzz")
    fun fizzbuzz(
        @RequestParam int1: Int,
        @RequestParam int2: Int,
        @RequestParam limit: Int,
        @RequestParam str1: String,
        @RequestParam str2: String
    ): ResponseEntity<List<String>> {
        val request = FizzBuzzRequest(int1, int2, limit, str1, str2)
        return ResponseEntity.ok(fizzBuzzService.fizzbuzz(request))
    }

    @GetMapping("/statistics")
    fun statistics(): ResponseEntity<StatsResponse> {
        return ResponseEntity.ok(fizzBuzzService.getStatistics())
    }
}