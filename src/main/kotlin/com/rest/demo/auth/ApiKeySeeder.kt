package com.rest.demo.auth

import com.rest.demo.entity.ApikeyEntity
import com.rest.demo.repository.ApikeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

/*
* this class will be run early when application running
* and will check if any api key on database
* */
@Component
class ApiKeySeeder(val apikeyRepository: ApikeyRepository): ApplicationRunner {
    val initialAPIKEY = "jemmy__calak__apikey"

    override fun run(args: ApplicationArguments?) {
        if(!apikeyRepository.existsById(initialAPIKEY)) {
            apikeyRepository.save(ApikeyEntity(initialAPIKEY))
        }
    }
}
