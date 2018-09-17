package com.springsecurity.bean;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MyLocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider serializers) throws IOException,JsonProcessingException  {
		// TODO Auto-generated method stub
		 DateTimeFormatter timeFormatter1 = DateTimeFormatter
		            .ofPattern("HH:mm:ss");
		gen.writeString(time.format(timeFormatter1));
		
	}

}
