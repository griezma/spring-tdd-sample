package griezma.springtdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    void setup() {
        carService = new CarService(carRepository);
    }

    @Test
    void getCarDetails_returnsCarInfo() {
        given(carRepository.findByName(anyString())).willReturn(Optional.of(new Car("prius", "hybrid")));

        var car = carService.getCarDetails("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test
    void getCarDetails_carNotFound() {
        given(carRepository.findByName(anyString())).willReturn(Optional.empty());

        assertThrows(CarNotFoundExcepion.class, () -> carService.getCarDetails("model s"));
    }
}