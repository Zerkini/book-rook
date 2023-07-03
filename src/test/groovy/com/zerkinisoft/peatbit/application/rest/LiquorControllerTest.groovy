package com.zerkinisoft.peatbit.application.rest

import com.zerkinisoft.peatbit.domain.Liquor
import com.zerkinisoft.peatbit.domain.LiquorService
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
@WebMvcTest(LiquorController.class)
class LiquorControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private LiquorService liquorService = Mock()

    def "adds a new liquor when post is performed"() {
        given:
        Liquor liquor

        String content = '''{
                            "name": "Lagavulin 16",
                            "type": "Whisky"
                        }'''

        when:
        mockMvc.perform(
                post("/liquor")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isOk())

        then:
        1 * liquorService.addLiquor(_ as Liquor) >> { arguments ->
            liquor = arguments.first() as Liquor
            liquor.id}

        liquor != null
        liquor.name == "Lagavulin 16"
        liquor.type == "Whisky"
    }

    def "gets liquor when get is performed"() {
        given:
        Integer id

        when:
        mockMvc.perform(get("/liquor/{id}", 2)).andExpect(status().isOk())

        then:
        1 * liquorService.getLiquor(_ as Integer) >> { arguments ->
            id = arguments.first() as Integer
            new Liquor()}

        id != null
        id == 2
    }

}
