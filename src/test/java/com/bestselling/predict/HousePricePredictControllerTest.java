package com.bestselling.predict;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.price.predict.datatransferobject.HouseDto;
import com.price.predict.domainvalue.ApartmentType;
import com.price.predict.domainvalue.Legalized;
import com.price.predict.domainvalue.Lift;
import com.price.predict.domainvalue.OwnerType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HousePricePredictControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnStatusCreatedWhenPostCarData() throws Exception {
        HouseDto requestBody = buildCardDTO();

        mvc.perform(MockMvcRequestBuilders.post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated());
    }

    private HouseDto buildCardDTO() {

		HouseDto house = new HouseDto();
		house.setSizeMeters(100);
		house.setHasLift(Lift.YES);
		house.setBuildingYear(new Date(2010,01,01));
		house.setOwnerType(OwnerType.INDIVIDUAL);
		house.setLegalized(Legalized.PA_HIPOTEKE);
		house.setCity("RrElbasanit");
 		house.setModelType(ApartmentType.TWO_PLUS_ONE);
 		house.setFurnished(Lift.YES);
 		return house;
     }
}