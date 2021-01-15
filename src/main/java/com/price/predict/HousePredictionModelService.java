package com.price.predict;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.price.predict.datatransferobject.HouseDto;
import com.price.predict.domainvalue.ALGORIRTHM;
import com.price.predict.domainvalue.ApartmentType;
import com.price.predict.domainvalue.Legalized;
import com.price.predict.domainvalue.Lift;
import com.price.predict.domainvalue.OwnerType;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class HousePredictionModelService {

	Logger logger = LoggerFactory.getLogger(HousePredictionModelService.class);

	public static final String TRAINING_DATA_SET_FILENAME_NAME = "house_data_1.arff";
	public static InputStream TRAINING_DATA_SET_FILE_PATH;
	public static AbstractClassifier repTree;
	public static AbstractClassifier randomForest;
	public static AbstractClassifier randomTree;
	public static AbstractClassifier zeroR;
	private static Instances instances;

	public HousePredictionModelService() throws Exception {
		if (TRAINING_DATA_SET_FILE_PATH == null) {
			InputStream resource11 = HousePredictionModelService.class.getClassLoader()
					.getResourceAsStream(TRAINING_DATA_SET_FILENAME_NAME);
			TRAINING_DATA_SET_FILE_PATH = resource11;
			instances = getInstances(TRAINING_DATA_SET_FILE_PATH);

		}
		createPredictionModel();
	}

	public HousePredictionModelService(ALGORIRTHM e) throws Exception {
		if (TRAINING_DATA_SET_FILE_PATH == null) {
			InputStream resource11 = HousePredictionModelService.class.getClassLoader()
					.getResourceAsStream(TRAINING_DATA_SET_FILENAME_NAME);
			TRAINING_DATA_SET_FILE_PATH = resource11;
			instances = getInstances(TRAINING_DATA_SET_FILE_PATH);
		}

		if (e.equals(ALGORIRTHM.RANDOM_TREE)) {
			createPredictionModel();
		} else if (e.equals(ALGORIRTHM.RANDOM_FOREST)) {
			createPredictionModelRondomForest();
		} else if (e.equals(ALGORIRTHM.ZEROR)) {
			createPredictionModelZeroR();
		} else if (e.equals(ALGORIRTHM.REP_TREE)) {
			createPredictionModelRepTree();
		}
	}

	public void createPredictionModel() throws Exception {

		if(randomTree ==null)
		randomTree = applyclassifier(new RandomTree(), instances,
				weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1"));
	}

	public void createPredictionModelRondomForest() throws Exception {

		if(randomForest ==null)
		randomForest = applyclassifier(new RandomForest(), instances,null
				//weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				);
	}

	public void createPredictionModelRepTree() throws Exception {

		if(repTree ==null)
		repTree = applyclassifier(new REPTree(), instances, null);
	}

	public void createPredictionModelZeroR() throws Exception {

		if(zeroR ==null)
		zeroR = applyclassifier(new ZeroR(), instances, null);
	}

	public AbstractClassifier applyclassifier(AbstractClassifier abstractClassifier, Instances trainingData,
			String[] options) throws Exception {

		abstractClassifier.buildClassifier(trainingData);
		abstractClassifier.setOptions(options);
		abstractClassifier.buildClassifier(trainingData);

		return abstractClassifier;
	}

	public Instances getInstances(InputStream trainingDataSetFile) throws Exception {

		DataSource source = new DataSource(trainingDataSetFile);
		Instances trainingData = source.getDataSet();
		if (trainingData.classIndex() == -1) // by default classIndex is -1
		{
			trainingData.setClassIndex(trainingData.numAttributes() - 1); // We need to setup classIndex to Number
																			// of
		}

		return trainingData;

	}

	public Instance createHouseInstance(HouseDto house) {
		Instance instance = new DenseInstance(8);

		instance.setDataset(instances);
		instances.setAttributeWeight(instances.attribute("sizeMeters"), 1.0);

		instance.setValue(instances.attribute("sizeMeters"), house.getSizeMeters());
		instance.setValue(instances.attribute("furnished"),
				house.getFurnished() != null ? house.getFurnished().name().toLowerCase()
						: Lift.NO.name().toLowerCase());
		instances.setAttributeWeight(instances.attribute("furnished"), 1.0);

		instance.setValue(instances.attribute("hasLift"),
				house.getHasLift() != null ? house.getHasLift().name().toLowerCase() : Lift.YES.name().toLowerCase());
		instances.setAttributeWeight(instances.attribute("hasLift"), 1.0);

		instance.setValue(instances.attribute("ownerType"),
				house.getOwnerType() != null ? house.getOwnerType().name().toLowerCase()
						: OwnerType.INDIVIDUAL.name().toLowerCase());
		instances.setAttributeWeight(instances.attribute("ownerType"), 1.0);

		instance.setValue(instances.attribute("legalized"),
				house.getLegalized() != null ? house.getLegalized().name().toLowerCase()
						: Legalized.ME_HIPOTEKE.name().toLowerCase());
		instances.setAttributeWeight(instances.attribute("legalized"), 1.0);

		instance.setValue(instances.attribute("buildingyear"),
				house.getBuildingYear() != null ? house.getBuildingYear().getYear() : 2000);
		instances.setAttributeWeight(instances.attribute("buildingyear"), 1.0);

		instance.setValue(instances.attribute("city"), house.getCity() != null ? house.getCity() : "Tirana");
		instances.setAttributeWeight(instances.attribute("city"), 1.0);

		instance.setValue(instances.attribute("type"),
				house.getModelType() != null ? house.getModelType().name().toLowerCase()
						: ApartmentType.ONE_PLUS_ONE.name().toLowerCase());
		instances.setAttributeWeight(instances.attribute("type"), 1.0);

		return instance;

	}

}
