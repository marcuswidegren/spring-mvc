package com.visma.spring;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.io.IOException;

public class DateTimeSerializer extends JsonSerializer<DateTime> {

@Override
public void serialize(DateTime dateTime, JsonGenerator jgen,
        SerializerProvider serProv) throws IOException,
        JsonProcessingException {
        System.out.println("Serializing: " + dateTime.toString());
        jgen.writeString(Long.toString(dateTime.getMillis()));
        }
        }