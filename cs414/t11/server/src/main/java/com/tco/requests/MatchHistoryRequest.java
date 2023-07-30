package com.tco.requests;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchHistoryRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(OngoingMatchesRequest.class);

    // response properties
    private ArrayList<LinkedHashMap<String,String>> matches;

    @Override
    public void buildResponse() {
        buildMatchesArrayList();
        log.trace("buildResponse -> {}", this);
    }

    public void buildMatchesArrayList() {
        LinkedHashMap<String,String> matchOne = new LinkedHashMap<>();
        matchOne.put("match_id", "0");
        matchOne.put("match_start", "01/01/21-00:00");
        matchOne.put("match_end", "01/01/21-00:01");
        matchOne.put("match_p1", "playerOne");
        matchOne.put("match_p2", "playerTwo");
        matchOne.put("match_winner", "playerOne");
        LinkedHashMap<String,String> matchTwo = new LinkedHashMap<>();
        matchTwo.put("match_id", "1");
        matchTwo.put("match_start", "01/01/21-01:00");
        matchTwo.put("match_end", "01/01/21-01:01");
        matchTwo.put("match_p1", "playerTwo");
        matchTwo.put("match_p2", "playerOne");
        matchTwo.put("match_winner", "playerTwo");
        LinkedHashMap<String,String> matchThree = new LinkedHashMap<>();
        matchThree.put("match_id", "2");
        matchThree.put("match_start", "01/01/21-02:00");
        matchThree.put("match_end", "01/01/21-02:01");
        matchThree.put("match_p1", "playerOne");
        matchThree.put("match_p2", "playerThree");
        matchThree.put("match_winner", "playerThree");
        LinkedHashMap<String,String> matchFour = new LinkedHashMap<>();
        matchFour.put("match_id", "3");
        matchFour.put("match_start", "01/01/21-03:00");
        matchFour.put("match_end", "01/01/21-03:01");
        matchFour.put("match_p1", "playerThree");
        matchFour.put("match_p2", "playerOne");
        matchFour.put("match_winner", "playerOne");

        matches = new ArrayList<>();
        matches.add(matchOne);
        matches.add(matchTwo);
        matches.add(matchThree);
        matches.add(matchFour);
    }
}