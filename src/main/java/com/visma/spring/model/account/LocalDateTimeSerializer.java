package com.visma.spring.model.account;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.joda.money.Money;
import org.joda.time.LocalDateTime;

import java.io.IOException;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator jgen,
                          SerializerProvider serProv) throws IOException,
            JsonProcessingException {
        System.out.println("Serializing: " + dateTime.toString());
        jgen.writeString(dateTime.toString());
    }
}