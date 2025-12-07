package com.glycotrack
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GlycoTrackApplication
fun main(args: Array<String>) { runApplication<GlycoTrackApplication>(*args) }