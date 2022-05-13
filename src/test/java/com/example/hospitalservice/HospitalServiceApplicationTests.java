package com.example.hospitalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.hospitalservice.dto.StaffDto;
import com.example.hospitalservice.entities.Staff;
import com.example.hospitalservice.services.StaffService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HospitalServiceApplicationTests {

	@Autowired
	StaffService staffService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void givenStaffUuidFetchPatientsUptoTwoYears() throws Exception {
		// given -->prepare the setup
		StaffDto staff = new StaffDto();
		staff.setName("Alex");
		Staff savedStaff = staffService.createStaff(staff);
		String staffUuid = savedStaff.getUuid();
		// when --> action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/patients/" + staffUuid));

		// action --> verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void givenStaffWrongUuidFetchPatientsUptoTwoYears() throws Exception {
		// given -->prepare the setup
		String wrongUuid = "205facf0-ad85-4bb0-a213-38c98f1da45";
		// when --> action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/patients/" + wrongUuid));

		// action --> verify the output
		response.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	void registerNewStaff() throws Exception {
		// given -->prepare the setup
		StaffDto staff = new StaffDto();
		staff.setName("Prince");
		Staff savedStaff = staffService.createStaff(staff);

		assertEquals("Prince", savedStaff.getName());
	}

}
