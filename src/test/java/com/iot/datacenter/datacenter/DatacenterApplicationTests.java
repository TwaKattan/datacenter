package com.iot.datacenter.datacenter;

import com.iot.datacenter.datacenter.controller.DataResultController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class DatacenterApplicationTests {

	private Logger logger = LoggerFactory.getLogger(DatacenterApplicationTests.class);

	@Autowired
	private MockMvc mockMvc;

	/**
	 * this test case is for save the sensor data into data base
	 * */
	@Test
	public void saveDeviceResultTest() throws Exception {
		logger.info("saveDeviceResultTest :- ###### Start");
		String newRide = "{\"deviceId\":\"c50b4fb7-a773-48b1-abb7-79b394502139\",\"temperature\":\"12.f\",\"humidity\":\"12.h\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/dataResult.json/createDeviceResult")
			.contentType(MediaType.APPLICATION_JSON).content(newRide)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		logger.info("saveDeviceResultTest :- ###### End");
	}

	/**
	 * this test case use to fetch the date from the database
	 * pagination api for fetch the data default its get the page 0 and limit 10
	 * */
	@Test
	public void getsDeviceResultTest() throws Exception {
		logger.info("getsDeviceResultTest :- ###### Start");
		String getsDeviceResult = "{\"columnName\":\"device_result.id\",\"order\":\"DESC\",\"page\":0,\"limit\":10}";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/dataResult.json/getAllDeviceResultInPagination")
			.contentType(MediaType.APPLICATION_JSON).content(getsDeviceResult)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		logger.info("getsDeviceResultTest :- ###### End");
	}

}
