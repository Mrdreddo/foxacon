package com.foxacons.base;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.foxacons.base.controller.FoxaconController;

@SpringBootApplication
public class FoxaconsApplication {

	public static void main(String[] args) {
//		new File(FoxaconController.uploadDirectory).mkdir();
		SpringApplication.run(FoxaconsApplication.class, args);
	}

}
