//package org.example.config;
//
//import org.example.CatRepository;
//import org.example.entities.Cat;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Profile("dev")
//public class DevDataInitializer implements ApplicationRunner {
//
//    private final CatRepository repository;
//
//    public DevDataInitializer(CatRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        if (repository.count() == 0) {
//            repository.saveAll(List.of(
//                    new Cat("Sigge", 13),
//                    new Cat("Maja", 3)
//            ));
//        }
//    }
//}
