package com.example.springboot.conf;

import com.example.springboot.transportation.Schedule;
import com.example.springboot.transportation.Station;
import com.example.springboot.transportation.Train;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.springboot.transportation")
public class AppConfig {
//    @Bean
//    public Train train(String number, String placesNumber) {
//        return  new Train(number, placesNumber);
//    }
//    @Bean
//    public Station station(String name) {
//        return new Station(name);
//    }
//
    @Bean
    public String String() {
        return new String();
    }
//
//    @Bean
//    public Schedule schedule() {
//        return new Schedule();
//    }
}
