package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.api.Match;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class MatchSerializationTest {

    @Test
    public void testMatchSerialization() throws IOException {
        MatchCriteriaImpl matchCriteria = new MatchCriteriaImpl();
        matchCriteria.setCriteriaDetails("true");

        MatchImpl match = new MatchImpl(UUID.randomUUID().toString(), "Match Order line", "ORDER_LINE", matchCriteria);
        match.addMatches(new MatchImpl(UUID.randomUUID().toString(), "Match Order line 1", "ORDER_LINE", matchCriteria));
        match.addNonMatches(new MatchImpl(UUID.randomUUID().toString(), "Match Order line 2", "ORDER_LINE", matchCriteria));

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(match);

        log.info(msg);

        Match match1 = om.readValue(msg, Match.class);

        assertEquals(match, match1);

    }

    @Test
    public void testMatchWithActionsSerialization() throws IOException {
        MatchCriteriaImpl matchCriteria = new MatchCriteriaImpl();
        matchCriteria.setCriteriaDetails("true");

        MatchImpl match = new MatchImpl(UUID.randomUUID().toString(), "Match Order line", "ORDER_LINE", matchCriteria);
        MatchImpl match2 = new MatchImpl(UUID.randomUUID().toString(), "Match Order line 1", "ORDER_LINE", matchCriteria);
        MatchImpl match3 = new MatchImpl(UUID.randomUUID().toString(), "Match Order line 2", "ORDER_LINE", matchCriteria);

        match.addMatches(match2);
        match.addNonMatches(match3);

        ActionImpl action = new ActionImpl(UUID.randomUUID().toString(), "Update Instance", "UPDATE", "INSTANCE", new MappingImpl());
        action.add(new ActionImpl(UUID.randomUUID().toString(), "Update holdings", "UPDATE", "HOLDINGS", new MappingImpl()));
        action.getChild(0).add(new ActionImpl(UUID.randomUUID().toString(), "Update item", "UPDATE", "ITEM", new MappingImpl()));

        match.addMatches(action);
        match2.addMatches(action);
        match3.addMatches(action);

        match.addNonMatches(action);
        match2.addNonMatches(action);
        match3.addNonMatches(action);

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(match);

        log.info(msg);

        Match match1 = om.readValue(msg, Match.class);

        assertEquals(match, match1);

    }


}
