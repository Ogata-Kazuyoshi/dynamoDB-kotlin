package com.example.testdoubletemplate.repository.dynamoDB

import com.example.testdoubletemplate.entity.TableEntity

interface BaseRepository {
    val dynamoDBRepository: NoSQLRepository<out TableEntity>
}