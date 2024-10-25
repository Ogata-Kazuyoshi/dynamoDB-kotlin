package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.TableEntity
import com.example.testdoubletemplate.repository.dynamoDB.NoSQLRepository

class SpyDynamoDBFactory: DynamoDBFactory {

    lateinit var dummy_retrunValue: NoSQLRepository<Any>
    lateinit var buid_argument: Class<Any>
    override fun <Table : TableEntity> build(tableClass: Class<Table>): NoSQLRepository<Table> {
        buid_argument = tableClass as Class<Any>
        return dummy_retrunValue as NoSQLRepository<Table>
    }
}

class StubDynamoDBFactory: DynamoDBFactory {

    lateinit var buid_returnValue: NoSQLRepository<Any>
    override fun <Table : TableEntity> build(tableClass: Class<Table>): NoSQLRepository<Table> {
        return buid_returnValue as NoSQLRepository<Table>
    }
}