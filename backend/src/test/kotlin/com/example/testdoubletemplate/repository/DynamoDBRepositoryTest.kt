//package com.example.testdoubletemplate.repository
//
//import org.junit.jupiter.api.Nested
//import org.junit.jupiter.api.Test
//import org.mockito.Mock
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//import org.springframework.boot.test.context.SpringBootTest
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
//import software.amazon.awssdk.enhanced.dynamodb.Key
//import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
//import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.beans.factory.annotation.Autowired
//import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable
//
//
//@SpringBootTest
//class DynamoDBRepositoryTest {
//
//    @MockBean
//    lateinit var dynamoDBTable: DynamoDbTable<Any>
//
//    lateinit var repository: DynamoDBRepository<Any>
//
//    @Nested
//    inner class findAllByPKのテスト {
//        @Test
//        fun 正しい値でdynamoDBTableのqueryメソッドを呼んでいること() {
//            // Arrange
//            val pk = "testPK"
//            val queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(pk).build())
//            val expectedRequest = QueryEnhancedRequest.builder().queryConditional(queryConditional).build()
//
//            // モックの設定
//            val mockPageIterable: PageIterable<Any> = mock(PageIterable::class.java)
//            val mockPage: Page<Any> = mock(Page::class.java)
//            `when`(mockPage.items()).thenReturn(listOf())
//            `when`(mockPageIterable.stream()).thenReturn(Stream.of(mockPage))
//            `when`(dynamoDBTable.query(any(QueryEnhancedRequest::class.java))).thenReturn(mockPageIterable)
//
//            // Act
//            repository.findAllByPK(pk)
//
//            // Assert
//            verify(dynamoDBTable).query(expectedRequest)
//        }
//    }
//}