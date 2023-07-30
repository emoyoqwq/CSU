package com.tco.requests;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchRequest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MatchRequest.class);
    private String match_id;
    private String user_id;
    private String match_board;


    @Override
    public void buildResponse() {
        // TODO: import SQL queries and use to build DB query for response
        //match_id = "0";
        //String query = "select from matches like " + match_id;
        //user_id = "dev1";
        match_board = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";//TODO call DAL for the match
        //match_board = DAL.getMatch(match_id, user_id);
        log.trace("buildResponse -> {}", this);
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public MatchRequest() {
        this.match_id = "0";
        this.user_id = "dev1";
        this.requestType = "match";
    }

    public String getMatchID() {
        return match_id;
    }

    public String getMatch() {
        return match_board;
    }
}