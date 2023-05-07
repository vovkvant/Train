package com.example.springboot;

import com.example.springboot.conf.AppConfig;
import com.example.springboot.transportation.Schedule;
import com.example.springboot.transportation.Station;
import com.example.springboot.transportation.Train;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context=  new AnnotationConfigApplicationContext(AppConfig.class);

		Train train = (Train) context.getBean(Train.class);
		// как передать параметры в конструктор??
		train.setNumber("444");
		train.setPlacesNumber("56");
		System.out.println("Got train: " + train);

		Schedule schedule = context.getBean(Schedule.class);
		schedule.setDate(new Date());
//		schedule.setTrain(train);
		System.out.println("Got schedule: " + schedule);

		Station station = context.getBean(Station.class);
		station.setName("New station");
		System.out.println("Got station: " + station);

		SpringApplication.run(Application.class, args);
	}

}
