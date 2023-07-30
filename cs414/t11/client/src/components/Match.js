import React, { Component } from 'react';
import { LOG } from '../utils/constants';
import { isJsonResponseValid, sendAPIRequest, getOriginalServerUrl } from '../utils/restfulAPI';

export default class Match extends Component{
    constructor(matchesJson, serverUrl) {
        super(matchesJson);
        this.matches = JSON.parse(matchesJson);

        this.sendMatchRequest = this.sendMatchRequest.bind(this);
        this.buildMatchRequest = this.buildMatchRequest.bind(this);
        this.serverUrl = serverUrl;
    }

    async sendMatchRequest() {
        const matchResponse = await sendAPIRequest({ requestType: "match" , user_id: "test"}, this.serverUrl);
        if (matchResponse) {
            // TODO: @HALTAB
            // DO SOME STUFF HERE IF IT WORKS
            console.log("I'm already... inside");
//            processServerConfigSuccess(matchResponse, this.serverUrl);
        } else {
            console.log(`Match request to ${this.serverUrl} failed. Check the log for more details.`, "error");
        }
    }

    buildMatchRequest() {
        return(JSON.stringify(this.matches))
    }
}
