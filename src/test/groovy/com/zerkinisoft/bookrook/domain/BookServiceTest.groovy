package com.zerkinisoft.bookrook.domain

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [BookService.class])
class BookServiceTest extends Specification {

    @Autowired
    private BookService underTest

    @SpringBean
    private BookRepository bookRepository = Mock()

    def "saves added book into the database"() {
        given:
        def book = new Book(10, "Jade War", "Fiction")
        Book capturedBook
        bookRepository.save(*_) >> 10

        when:
        def bookId = underTest.addBook(book)

        then:
        1 * bookRepository.save(_ as Book) >> { arguments ->
            capturedBook = arguments.first() as Book
        }

        capturedBook.id == 10
        capturedBook.name == "Jade War"
        capturedBook.type == "Fiction"
        bookId == 10;
    }


}
