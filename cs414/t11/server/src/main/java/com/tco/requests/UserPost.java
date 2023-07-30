package com.tco.requests;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPost extends Request {

    private final transient Logger log = LoggerFactory.getLogger(MatchRequest.class);
    private String nickname;
    private String email;
    private String password_hash;

    @Override
    public void buildResponse() {
        //DAL.createUser(nickname, email, password_hash);
        log.trace("buildResponse -> {}", this);
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public UserPost() {
        this.nickname = "dev1";
        this.email = "dev1@needcoffee.com";
        this.password_hash = "fjeiwopqga";
        this.requestType = "user";
    }
}