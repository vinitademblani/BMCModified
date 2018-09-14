package com.springsecurity.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.bean.City;
import com.springsecurity.controller.BookMyShowController;
import com.springsecurity.service.CityService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=BookMyShowController.class,secure = false)
public class BookMyShowControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService service;
	
	@Test
	public void testgetAllCities() throws Exception {
		
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		City c2=new City();
		c2.setCityId(2L);
		c2.setCityName("pune");
		
		List<City> expected=new ArrayList<>();
		expected.add(c1);
		expected.add(c2);
		
		//String inputInJson = this.mapToJson(expected);
		
		String URI = "/cities";
		
		Mockito.when(service.getAllCities()).thenReturn(expected);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		String expectedJson = this.mapToJson(expected);
		
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(expectedJson).isEqualTo(outputInJson);
		//assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*@Test
	public void testGetTicketById() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceStation("Kolkata");
		mockTicket.setDestStation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");
		
		Mockito.when(ticketBookingService.getTicketById(Mockito.anyInt())).thenReturn(mockTicket);
		
		String URI = "/api/tickets/ticketId/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockTicket);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetAllBookedTickets() throws Exception {

		Ticket mockTicket1 = new Ticket();
		mockTicket1.setTicketId(1);
		mockTicket1.setPassengerName("Martin Bingel");
		mockTicket1.setSourceStation("Kolkata");
		mockTicket1.setDestStation("Delhi");
		mockTicket1.setBookingDate(new Date());
		mockTicket1.setEmail("martin.s2017@gmail.com");
		
		Ticket mockTicket2 = new Ticket();
		mockTicket2.setPassengerName("Sean Murphy");
		mockTicket2.setSourceStation("Kolkata");
		mockTicket2.setDestStation("Mumbai");
		mockTicket2.setBookingDate(new Date());
		mockTicket2.setEmail("sean.s2017@gmail.com");
		
		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(mockTicket1);
		ticketList.add(mockTicket2);
		
		Mockito.when(ticketBookingService.getAllBookedTickets()).thenReturn(ticketList);
		
		String URI = "/api/tickets/alltickets";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(ticketList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetTicketByEmail() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceStation("Kolkata");
		mockTicket.setDestStation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");
		
		String expectedJson = this.mapToJson(mockTicket);
		
		Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);
		
		String URI = "/api/tickets/email/martin.s2017@gmail.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	
	}*/

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
