package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.repository.dynamoDB.DynamoDBRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import org.springframework.boot.test.mock.mockito.MockBean
import software.amazon.awssdk.enhanced.dynamodb.model.Page
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable


data class TestEntity (
    val id: String = ""
)

@SpringBootTest
class DynamoDBRepositoryTest {

    @MockBean
    lateinit var dynamoDBTable: DynamoDbTable<TestEntity>

    lateinit var dynamoDBRepository: DynamoDBRepository<TestEntity>

    @BeforeEach
    fun setUp() {
        dynamoDBRepository = DynamoDBRepository(dynamoDBTable)
    }


    @Nested
    inner class findAllByPKのテスト {
        @Test
        fun 正しい値でdynamoDBTableのqueryメソッドを呼んでいること() {
            val pk = "testPK"
            val queryConditional = QueryConditional
                .keyEqualTo(
                    Key.builder()
                        .partitionValue(pk)
                        .build()
                )
            val pageIterable = mock<PageIterable<TestEntity>>()
            val page = mock<Page<TestEntity>>()
            `when`(page.items()).thenReturn(emptyList())
            `when`(pageIterable.iterator()).thenReturn(mutableListOf(page).iterator())
            `when`(dynamoDBTable.query(any<QueryConditional>())).thenReturn(pageIterable)

            dynamoDBRepository.findAllByPK(pk)

            verify(dynamoDBTable).query(queryConditional)
        }

        @Test
        fun queryの結果をflatMapでフラット化して返すこと() {
            val dummyPk = "dummyPK"
            val itemsPage = listOf(TestEntity(id = "1"), TestEntity(id = "2"))
            val page = mock<Page<TestEntity>>()
            `when`(page.items()).thenReturn(itemsPage)

            val pageIterable = mock<PageIterable<TestEntity>>()
            `when`(pageIterable.iterator()).thenReturn(mutableListOf(page).iterator())

            `when`(dynamoDBTable.query(any<QueryConditional>())).thenReturn(pageIterable)

            val dynamoDBRepository = DynamoDBRepository(dynamoDBTable)
            val result = dynamoDBRepository.findAllByPK(dummyPk)

            assertEquals(itemsPage, result)
        }
    }
}