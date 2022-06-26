package com.publicis.sapient.creditcard;

import com.publicis.sapient.creditcard.app.CreditCardApplication;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.publicis.sapient.creditcard.app.util.CreditCardConstant.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.AnyOf.*;


/**
 * Credit card application integration test class
 */
@SpringBootTest(classes = {CreditCardApplication.class})
@AutoConfigureMockMvc
class CreditCardApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	private static final String API_ADD_CARD = "/creditcard/api/v1/addCard";
	private static final String API_GET_ALL_CARD = "/creditcard/api/v1/getAllCards";

	@Test
	public void testAddCardDetailsWithInvalidHttpMethod()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.get(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"cardNumber\":\"1358954993914435\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message").value("Request method 'GET' not supported"));
	}


	@Test
	public void testAddCardDetailsWithoutName()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"cardNumber\":\"1358954993914435\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(EMPTY_NAME));

	}

	@Test
	public void testAddCardDetailsWithoutCardNumber()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"name\":\"test\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message",anyOf(is(INVALID_CARD_NUMBER),is(CREDIT_CARD_NUMBER_NOT_LUHN))));

	}

	@Test
	public void testAddCardDetailsWithMoreThen20CardNumber()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"name\":\"test\",\n" +
								"    \"cardNumber\":\"13589549939144358888\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message",anyOf(is(INVALID_CARD_NUMBER),is(CREDIT_CARD_NUMBER_NOT_LUHN))));

	}

	@Test
	public void testAddCardDetailsWithoutLimit()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"name\":\"test\",\n" +
								"    \"cardNumber\":\"1358954993914435\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(INVALID_CARD_LIMIT));
	}

	@Test
	public void testAddCardDetailsWithInvalidLuhnCardNumber()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"name\":\"test\",\n" +
								"    \"cardNumber\":\"13589549945\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(CREDIT_CARD_NUMBER_NOT_LUHN));
	}

	@Test
	@Order(1)
	public void testAddCardDetails()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"name\":\"test\",\n" +
								"    \"cardNumber\":\"1358954993914435\",\n" +
								"    \"limit\":\"300\"\n" +
								"}"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(jsonPath("$.message").value(CARD_ADDED_SUCCESSFULLY));
	}

	@Test
	public void testGetCardDetails()  throws Exception
	{

		mockMvc.perform(MockMvcRequestBuilders.post(API_ADD_CARD)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"    \"name\":\"test\",\n" +
						"    \"cardNumber\":\"1358954993914435\",\n" +
						"    \"limit\":\"300\"\n" +
						"}"));

		mockMvc.perform(MockMvcRequestBuilders.get(API_GET_ALL_CARD)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(jsonPath("$.data.cardList[0].name").value("test"));
	}

	@Test
	void contextLoads() {
	}

}
