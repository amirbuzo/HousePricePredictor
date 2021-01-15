package com.price.predict.datatransferobject;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.price.predict.domainvalue.ALGORIRTHM;
import com.price.predict.domainvalue.ApartmentType;
import com.price.predict.domainvalue.Legalized;
import com.price.predict.domainvalue.Lift;
import com.price.predict.domainvalue.OwnerType;
 

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseDto {

    private Integer sizeMeters=0;
    private Lift furnished=Lift.NO;
    private OwnerType ownerType = OwnerType.INDIVIDUAL;
    private Lift hasLift = Lift.YES;
    private Legalized legalized = Legalized.ME_HIPOTEKE;
    private Date buildingYear = new Date(100, 1, 1);
    private String city = "Blloku";
    private ApartmentType modelType = ApartmentType.ONE_PLUS_ONE;
    private Double price;
    private ALGORIRTHM algorithm = ALGORIRTHM.RANDOM_FOREST;

    
	public Integer getSizeMeters() {
		return sizeMeters;
	}
	public void setSizeMeters(Integer sizeMeters) {
		this.sizeMeters = sizeMeters;
	}
	 
	public Lift getFurnished() {
		return furnished;
	}
	public void setFurnished(Lift furnished) {
		this.furnished = furnished;
	}
	public OwnerType getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(OwnerType ownerType) {
		this.ownerType = ownerType;
	}
	public Lift getHasLift() {
		return hasLift;
	}
	public void setHasLift(Lift hasLift) {
		this.hasLift = hasLift;
	}
	public Legalized getLegalized() {
		return legalized;
	}
	public void setLegalized(Legalized legalized) {
		this.legalized = legalized;
	}
	
	 @JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy", timezone = "IST")
	public Date getBuildingYear() {
		return buildingYear;
	}
	public void setBuildingYear(Date buildingYear) {
		this.buildingYear = buildingYear;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public ApartmentType getModelType() {
		return modelType;
	}
	public void setModelType(ApartmentType modelType) {
		this.modelType = modelType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public ALGORIRTHM getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(ALGORIRTHM algorithm) {
		this.algorithm = algorithm;
	}
	@Override
	public String toString() {
		return "HouseDto [sizeMeters=" + sizeMeters + ", furnished=" + furnished + ", ownerType=" + ownerType
				+ ", hasLift=" + hasLift + ", legalized=" + legalized + ", buildingYear=" + buildingYear + ", city="
				+ city + ", modelType=" + modelType + ", price=" + price + ", algorithm=" + algorithm + "]";
	}
	 
    
	
	 

}
