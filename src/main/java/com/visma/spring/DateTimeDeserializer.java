package com.visma.spring;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.io.IOException;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        long millis = Long.parseLong(parser.getText());
        return new DateTime(millis);
    }

}
