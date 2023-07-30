package com.tco.requests;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchPost extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MatchRequest.class);
    private String match_id;
    private String user_id;


    @Override
    public void buildResponse() {
        match_id = "0";//TODO create a match through the DAL
        //match_id = DAL.createMatch(user_id);
        log.trace("buildResponse -> {}", this);
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public MatchPost() {
        this.match_id = "0";
        this.user_id = "dev1";
        this.requestType = "match";
    }

    public String getMatchID() {
        return match_id;
    }
}