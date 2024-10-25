package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.SpyDynamoDBFactory
import com.example.testdoubletemplate.config.StubDynamoDBFactory
import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.repository.dynamoDB.NoSQLRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


class DefaultFAQRepositoryTest {

    private lateinit var dynamoDBRepository: NoSQLRepository<InformationTableEntity>
    private lateinit var faqRepository: FAQRepository

    @Nested
    inner class dynamoDBRepositoryメンバー変数のセットに関して () {
        val spyDynamoDBFactory = SpyDynamoDBFactory()
        @BeforeEach
        fun setUp() {
            dynamoDBRepository = mockk(relaxed = true)
            spyDynamoDBFactory.dummy_retrunValue = dynamoDBRepository as NoSQLRepository<Any>

            faqRepository = DefaultFAQRepository(spyDynamoDBFactory)
        }
        @Test
        fun 正しいクラスでdynamoDBFactoryのbuidメソッドが呼ばれていること(){
            faqRepository.findAllFAQList()

            assertEquals(InformationTableEntity::class.java,spyDynamoDBFactory.buid_argument)
        }
    }


    @Nested
    inner class 各メソッドに対して {
        @BeforeEach
        fun setUp() {
            dynamoDBRepository = mockk(relaxed = true)
            val stubDynamoDBFactory = StubDynamoDBFactory()
            stubDynamoDBFactory.buid_returnValue = dynamoDBRepository as NoSQLRepository<Any>

            faqRepository = DefaultFAQRepository(stubDynamoDBFactory)
        }
        @Test
        fun `findAllByPKを正しい引数で呼んでいること`() {
            faqRepository.findAllFAQList()

            verify { dynamoDBRepository.findAllByPK("FAQ") }
        }

        @Test
        fun findAllByPKの返り値を正しく返すこと(){
            val infomationTable1 = InformationTableEntity(
                answer = "A1",
                question = "Q1"
            )
            val infomationTable2 = InformationTableEntity(
                answer = "A2",
                question = "Q2"
            )
            val stubFindAllByPKs = listOf(infomationTable1, infomationTable2)

            every { dynamoDBRepository.findAllByPK(any()) } returns stubFindAllByPKs

            val res = faqRepository.findAllFAQList()

            assertEquals("A1" , res[0].answer)
            assertEquals("Q1" , res[0].question)
            assertEquals("A2" , res[1].answer)
            assertEquals("Q2" , res[1].question)
        }
    }
}