package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.api.Action;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ActionSerializationTest {

    @Test
    public void testActionSerialization() throws IOException {
        ActionImpl action = new ActionImpl(UUID.randomUUID().toString(), "Update Instance", "UPDATE", "INSTANCE", new MappingImpl());
        action.add(new ActionImpl(UUID.randomUUID().toString(), "Update holdings", "UPDATE", "HOLDINGS", new MappingImpl()));
        action.add(new ActionImpl(UUID.randomUUID().toString(), "Update holdings 2", "UPDATE", "HOLDINGS", new MappingImpl()));
        action.getChild(0).add(new ActionImpl(UUID.randomUUID().toString(), "Update item", "UPDATE", "ITEM", new MappingImpl()));

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(action);

        log.info(msg);

        Action action1 = om.readValue(msg, Action.class);

        assertEquals(action, action1);

    }

    @Test
    public void testActionWithMatchesSerialization() throws IOException {
        ActionImpl action = new ActionImpl(UUID.randomUUID().toString(), "Update Instance", "UPDATE", "INSTANCE", new MappingImpl());
        action.add(new ActionImpl(UUID.randomUUID().toString(), "Update holdings", "UPDATE", "HOLDINGS", new MappingImpl()));
        action.getChild(0).add(new ActionImpl(UUID.randomUUID().toString(), "Update item", "UPDATE", "ITEM", new MappingImpl()));

        MatchCriteriaImpl matchCriteria = new MatchCriteriaImpl();
        matchCriteria.setCriteriaDetails("true");

        MatchImpl match = new MatchImpl(UUID.randomUUID().toString(), "Match Order line", "ORDER_LINE", matchCriteria);
        MatchImpl match2 = new MatchImpl(UUID.randomUUID().toString(), "Match Order line 1", "ORDER_LINE", matchCriteria);
        MatchImpl match3 = new MatchImpl(UUID.randomUUID().toString(), "Match Order line 2", "ORDER_LINE", matchCriteria);

        match.addMatches(match2);
        match.addNonMatches(match3);

        ActionImpl action2 = new ActionImpl(UUID.randomUUID().toString(), "Update Instance", "UPDATE", "INSTANCE", new MappingImpl());
        action2.add(new ActionImpl(UUID.randomUUID().toString(), "Update holdings", "UPDATE", "HOLDINGS", new MappingImpl()));
        action2.getChild(0).add(new ActionImpl(UUID.randomUUID().toString(), "Update item", "UPDATE", "ITEM", new MappingImpl()));

        match.addMatches(action2);
        match2.addMatches(action2);
        match3.addMatches(action2);

        match.addNonMatches(action2);
        match2.addNonMatches(action2);
        match3.addNonMatches(action2);

        action.add(match);

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(action);

        log.info(msg);

        Action action1 = om.readValue(msg, Action.class);

        assertEquals(action, action1);
    }

}
