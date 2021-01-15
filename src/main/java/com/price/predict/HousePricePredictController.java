package com.price.predict;

import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.price.predict.datatransferobject.HouseDto;
import com.price.predict.domainvalue.ALGORIRTHM;

import ch.qos.logback.core.net.SyslogOutputStream;
import weka.core.Instance;



@RestController
@RequestMapping("v1/house")
public class HousePricePredictController {

	HousePredictionModelService predictionModelService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HouseDto createCar(@Valid @RequestBody HouseDto houseDto) throws Exception {

		System.out.println(houseDto);
		predictionModelService = new HousePredictionModelService(houseDto.getAlgorithm());
		Instance createCarInstance = predictionModelService.createHouseInstance(houseDto);
		System.out.println(createCarInstance);

		ALGORIRTHM e = houseDto.getAlgorithm();
		double result = 0.0;

		if (e.equals(ALGORIRTHM.RANDOM_TREE)) {
			result = HousePredictionModelService.randomTree.classifyInstance(createCarInstance);
		} else if (e.equals(ALGORIRTHM.RANDOM_FOREST)) {
			result = HousePredictionModelService.randomForest.classifyInstance(createCarInstance);

		} else if (e.equals(ALGORIRTHM.ZEROR)) {
			result = HousePredictionModelService.zeroR.classifyInstance(createCarInstance);

		} else if (e.equals(ALGORIRTHM.REP_TREE)) {
			result = HousePredictionModelService.repTree.classifyInstance(createCarInstance);
		}

		houseDto.setPrice(result);
		return houseDto;
	}

}
