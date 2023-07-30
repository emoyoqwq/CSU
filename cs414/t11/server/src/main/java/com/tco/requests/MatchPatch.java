package com.tco.requests;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchPatch extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MatchRequest.class);
    private boolean active;
    private String match_id;
    private String user_id;
    private String opponent_id;
    private String turn_id;
    private String match_board;



    @Override
    public void buildResponse() {
        match_id = "0";//TODO create a match through the DAL
        //match_id = DAL.replaceMatch(match_id, user_id, opponent_id, turn_id, active, match_board);
        log.trace("buildResponse -> {}", this);
        active = false;
        user_id = null;
        opponent_id = null;
        turn_id = null;
        match_board = null;
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public MatchPatch() {
        this.match_id = "0";
        this.user_id = "dev1";
        this.opponent_id = "dev2";
        this.turn_id = "dev1";
        this.active = true;
        this.match_board = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        this.requestType = "match";
    }

    public String getMatchID() {
        return match_id;
    }

    public String getUserID() {
        return user_id;
    }

    public String getOpponentID() {
        return opponent_id;
    }

    public String getTurnID() {
        return turn_id;
    }

    public boolean getActive() {
        return active;
    }

    public String getMatchBoard() {
        return match_board;
    }
}