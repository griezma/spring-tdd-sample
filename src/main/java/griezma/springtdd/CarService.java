package griezma.springtdd;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new CarNotFoundExcepion());
    }
}
