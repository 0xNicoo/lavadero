package com.nicolas.Lavadero.util;

import com.nicolas.Lavadero.model.ServiceType;
import com.nicolas.Lavadero.repository.ServiceTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ServiceTypeRepository serviceTypeRepository;

    public DataInitializer(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(serviceTypeRepository.count() == 0){
            ServiceType lavadoExterior = new ServiceType();
            ServiceType lavadoInterior = new ServiceType();
            ServiceType ambos = new ServiceType();
            lavadoExterior.setName("Lavado exterior");
            lavadoInterior.setName("Lavado interior");
            ambos.setName("Ambos");
            serviceTypeRepository.saveAll(List.of(lavadoExterior, lavadoInterior, ambos));
        }
    }
}
