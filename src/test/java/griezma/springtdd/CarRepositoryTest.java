package griezma.springtdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    CarRepository repository;

    @Test
    void findByName_returnsCar() {
        Car savedCar = em.persistFlushFind(new Car("prius", "hybrid"));

        Car car = repository.findByName("prius").orElseThrow();

        assertThat(car).isEqualTo(savedCar);
    }
}