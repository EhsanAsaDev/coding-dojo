package com.assignment.spring.repository;

import com.assignment.spring.domain.WeatherEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import javax.persistence.EntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Ehsan Sh
 */

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*@TestPropertySource(properties = {
        "spring.flyway.enabled=false"
})*/
public class WeatherRepositoryUnitTest {

    @Autowired
    public TestEntityManager testEntityManager;
    @Autowired
    public WeatherRepository weatherRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    void injectedComponentsAreNotNull() {
        assertThat(testEntityManager).isNotNull();
        assertThat(weatherRepository).isNotNull();
    }

    @Test
    public void after_save_one_record_one_must_be_fetched() {
        //given
        WeatherEntity weatherEntity = new WeatherEntity(null, "tehran", "IR", 23.12);
        testEntityManager.persist(weatherEntity);
        testEntityManager.flush();

        //when
        WeatherEntity weatherEntity1 = weatherRepository.findById(weatherEntity.getId()).get();

        //Except
        Assertions.assertThat(weatherEntity.getCountry()).isEqualTo(weatherEntity1.getCountry());
        Assertions.assertThat(weatherEntity.getTemperature()).isEqualTo(weatherEntity1.getTemperature());
    }

}   
