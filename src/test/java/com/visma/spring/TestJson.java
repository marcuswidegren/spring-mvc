package com.visma.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visma.spring.model.account.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestJson {
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testTransaction() {
        assertTrue(mapper.canDeserialize(mapper.constructType(Transaction.class)));
    }

    @Test
    public void testSerialize() {
        assertTrue(mapper.canSerialize(Transaction.class));
    }


}
