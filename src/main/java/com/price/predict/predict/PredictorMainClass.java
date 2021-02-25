package com.price.predict.predict;

import java.util.Date;

import com.price.predict.datatransferobject.HouseDto;
import com.price.predict.domainvalue.ApartmentType;
import com.price.predict.domainvalue.Legalized;
import com.price.predict.domainvalue.Lift;
import com.price.predict.domainvalue.OwnerType;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.RegressionByDiscretization;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.M5Rules;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.lmt.*;
import weka.clusterers.RandomizableDensityBasedClusterer;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.NominalToBinary;
 
public class PredictorMainClass {

	public static final String TRAINING_DATA_SET_FILENAME_NUM = "C:\\Users\\abuzo\\house_data_1.arff";
   // public static final String TRAINING_DATA_SET_FILENAME_NOMINAL = "C:\\Users\\abuzo\\house_data_1nominal.arff";
	
	public static void main(String[] args) throws Exception {

		

		HouseDto house = new HouseDto();
		house.setSizeMeters(100);
		house.setHasLift(Lift.YES);
		house.setBuildingYear(new Date(2010,01,01));
		house.setOwnerType(OwnerType.INDIVIDUAL);
		house.setLegalized(Legalized.ME_HIPOTEKE);
		house.setCity("Fresku");
 		house.setModelType(ApartmentType.TWO_PLUS_ONE);
 		house.setFurnished(Lift.YES);
 		System.out.println("housedto" + house);
 
 	 
// 		System.out.println(randomForestPediction(house));
// 		System.out.println(randomTreePediction(house));
// 		System.out.println(repTreePediction(house));
 		System.out.println(zeroRPediction(house));
 //		System.out.println(decisionStumpSPediction(house));
 	 
 
	}
	
	public static Double randomTreePediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new RandomTree(), instances,
				 weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("randomTree =" + result);
		return result; 
	}
	
	
	public static Double randomForestPediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new RandomForest(), instances,
				 weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("randomForestPediction =" + result);
		return result; 
	}
	
	public static Double repTreePediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new REPTree(), instances,
				 null//weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("repTree=" + result);
		return result; 
	}

	public static Double zeroRPediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new RandomForest(), instances,
				null// weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("zeroR=" + result);
 		  result = applyclassifier.classifyInstance(houseInstance);
 			System.out.println("zeroR=" + result);

		return result; 
	}
	
	public static Double decisionStumpSPediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new DecisionStump(), instances,
				null// weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("DecisionStump=" + result);
		return result; 
	}
	
	public static Double lmtSPediction(HouseDto house) throws Exception
	{
		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NOMINAL);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new M5Rules(), instances,
				null// weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1")
				 );
		
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
 		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("j48=" + result);
		return result; 
	}
	


	public static void createPredictionModelNominal(HouseDto house) throws Exception {

		PredictorMainClass inputInstance = new PredictorMainClass();
		Instances instances = inputInstance.getInstances(TRAINING_DATA_SET_FILENAME_NUM);
		System.out.println(instances);

		Instances nomintalInstances = nominalToBinary(instances);
		AbstractClassifier applyclassifier = inputInstance.applyclassifier(new LinearRegression(), nomintalInstances,
				null);
 
		Instance houseInstance = inputInstance.createhouseInstance(instances, house);
		
		Instance nominalHouse =nominalToBinary(houseInstance);
		System.out.println(nominalHouse);
		double result = applyclassifier.classifyInstance(houseInstance);
		System.out.println("createPredictionModel" + result);
	}
	
	private static Instances nominalToBinary( Instances insts ) throws Exception {
		  boolean onlyNumeric = true;
		  for (int i = 0; i < insts.numAttributes(); i++) {
		    if (i != insts.classIndex()) {
		      if (!insts.attribute(i).isNumeric()) {
		        onlyNumeric = false;
		        break;
		      }
		    }
		  }

		  if (!onlyNumeric) {
			  NominalToBinary  m_NominalToBinary = new NominalToBinary();
		    m_NominalToBinary.setInputFormat(insts);
		    insts = Filter.useFilter(insts, m_NominalToBinary);
		  }
		  return insts;
		}
	
	private static Instance nominalToBinary( Instance insts ) throws Exception {
		  boolean onlyNumeric = true;
		  for (int i = 0; i < insts.numAttributes(); i++) {
		    if (i != insts.classIndex()) {
		      if (!insts.attribute(i).isNumeric()) {
		        onlyNumeric = false;
		        break;
		      }
		    }
		  }

		  if (!onlyNumeric) {
			  NominalToBinary  m_NominalToBinary = new NominalToBinary();
			  
 		  }
		  return insts;
		}

	private Instance createhouseInstance(Instances instances, HouseDto house) {
		Instance instance = new DenseInstance(8);
		 
		instance.setDataset(instances);

		instance.setValue(instances.attribute("sizeMeters"), house.getSizeMeters());
 		instance.setValue(instances.attribute("furnished"), house.getFurnished()!= null ? house.getFurnished().name().toLowerCase() : Lift.NO.name().toLowerCase());
		

		instance.setValue(instances.attribute("hasLift"),
				house.getHasLift() != null ? house.getHasLift().name().toLowerCase() : Lift.YES.name().toLowerCase());
		
		instance.setValue(instances.attribute("ownerType"),
				house.getOwnerType() != null ? house.getOwnerType().name().toLowerCase()
						: OwnerType.INDIVIDUAL.name().toLowerCase());
		
		instance.setValue(instances.attribute("legalized"),
				house.getLegalized() != null ? house.getLegalized().name().toLowerCase()
						: Legalized.ME_HIPOTEKE.name().toLowerCase());
		
		instance.setValue(instances.attribute("buildingyear"),
				house.getBuildingYear() != null ? house.getBuildingYear().getYear() : 2000);
		
		instance.setValue(instances.attribute("city"), house.getCity() != null ? house.getCity() : "Fresku");
		
		instance.setValue(instances.attribute("type"),
				house.getModelType() != null ? house.getModelType().name().toLowerCase()
						: ApartmentType.ONE_PLUS_ONE.name().toLowerCase());
		
		return instance;

	}

	public AbstractClassifier applyclassifier(AbstractClassifier abstractClassifier, Instances trainingData,
			String[] options) throws Exception {

		abstractClassifier.buildClassifier(trainingData);
		abstractClassifier.setOptions(options);

		abstractClassifier.buildClassifier(trainingData);

		return abstractClassifier;
	}

	public Instances getInstances(String trainingDataSetFile) throws Exception {

		DataSource source = new DataSource(trainingDataSetFile);
		Instances instances = source.getDataSet();
		instances.setAttributeWeight(instances.attribute("type"),  1.0);
		instances.setAttributeWeight(instances.attribute("city"),  1.0);
		instances.setAttributeWeight(instances.attribute("buildingyear"),  1.0);
		instances.setAttributeWeight(instances.attribute("legalized"), 1.0);
		instances.setAttributeWeight(instances.attribute("ownerType"), 1.0);
		instances.setAttributeWeight(instances.attribute("hasLift"), 1.0);
		instances.setAttributeWeight(instances.attribute("furnished"), 1.0);
		instances.setAttributeWeight(instances.attribute("sizeMeters"), 1.0);
		if (instances.classIndex() == -1) // by default classIndex is -1
		{
			instances.setClassIndex(instances.numAttributes() - 1); // We need to setup classIndex to Number
																			// of
		}
		return instances;

	}
}
