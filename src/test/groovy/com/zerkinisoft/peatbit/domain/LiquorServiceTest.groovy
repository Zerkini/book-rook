package com.zerkinisoft.peatbit.domain

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [LiquorService.class])
class LiquorServiceTest extends Specification {

    @Autowired
    private LiquorService underTest

    @SpringBean
    private LiquorRepository liquorRepository = Mock()

    def "saves added liquor into the database"() {
        given:
        def liquor = new Liquor(10, "Ardbeg Uigeadail", "Whisky")
        Liquor capturedLiquor
        liquorRepository.save(*_) >> 10

        when:
        def liquorId = underTest.addLiquor(liquor)

        then:
        1 * liquorRepository.save(_ as Liquor) >> { arguments ->
            capturedLiquor = arguments.first() as Liquor
        }

        capturedLiquor.id == 10
        capturedLiquor.name == "Ardbeg Uigeadail"
        capturedLiquor.type == "Whisky"
        liquorId == 10;
    }


}
