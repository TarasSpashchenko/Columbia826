package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.api.Action;
import com.folijet.columbia.core.metadata.api.Job;
import com.folijet.columbia.core.metadata.api.Match;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JobSerializationTest {

    @Test
    public void testJobSerialization() throws IOException {
        Job job = new JobImpl(UUID.randomUUID().toString(), "Test Level 01");
        job.add(new JobImpl(UUID.randomUUID().toString(), "Test Level 02"));

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(job);

        log.info(msg);

        Job job1 = om.readValue(msg, Job.class);

        assertEquals(job, job1);

    }

    @Test
    public void testJobCompleteSerialization() throws IOException {
        Job job = new JobImpl(UUID.randomUUID().toString(), "Test Level 01");

        job.add(getCompleteMatchWithActions());
        job.add(getCompleteActionWithMatches());
        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(job);

        log.info(msg);

        Job job1 = om.readValue(msg, Job.class);

        assertEquals(job, job1);

    }

    private Action getCompleteActionWithMatches() throws IOException {
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

        return action;
    }

    private Match getCompleteMatchWithActions() throws IOException {
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

        return match;
    }
}
