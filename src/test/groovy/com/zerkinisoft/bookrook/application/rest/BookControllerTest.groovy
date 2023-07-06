package com.zerkinisoft.bookrook.application.rest

import com.zerkinisoft.bookrook.domain.Book
import com.zerkinisoft.bookrook.domain.BookService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(BookController.class)
class BookControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private BookService bookService = Mock()

    def "adds a new book when post is performed"() {
        given:
        Book book

        String content = '''{
                            "name": "Jade City",
                            "type": "Fiction"
                        }'''

        when:
        mockMvc.perform(
                post("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isOk())

        then:
        1 * bookService.addBook(_ as Book) >> { arguments ->
            book = arguments.first() as Book
            book.id
        }

        book != null
        book.name == "Jade City"
        book.type == "Fiction"
    }

    def "gets book when get is performed"() {
        given:
        Integer id

        when:
        mockMvc.perform(get("/book/{id}", 2)).andExpect(status().isOk())

        then:
        1 * bookService.getBook(_ as Integer) >> { arguments ->
            id = arguments.first() as Integer
            new Book()
        }

        id != null
        id == 2
    }

}
