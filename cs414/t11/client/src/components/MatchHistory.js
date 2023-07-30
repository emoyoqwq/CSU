import React, { Component } from 'react';
import { sendAPIRequest } from '../utils/restfulAPI';
import { Button, Table } from "reactstrap";

export default class MatchHistory extends Component {
    constructor(props) {
        super(props);

        this.state = {
            matchHistoryArray: []
        }
        this.user_id = props.user_id;
        this.serverUrl = props.serverUrl;
    }

    render() {
        return (
            <>
                {this.renderMatchHistoryTable()}
                {this.renderRefreshButton()}
            </>
        );
    }

    renderMatchHistoryTable() {
        return (
            <Table>
                <thead>
                <tr>
                    <th>Start Date & Time</th>
                    <th>End Date & Time</th>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Winner</th>
                </tr>
                </thead>
                <tbody>
                {this.renderMatchHistoryTableRows()}
                </tbody>
            </Table>
        );
    }

    renderMatchHistoryTableRows() {
        let rows = [];
        for (let match of this.state.matchHistoryArray) {
            rows.push(
                <tr key={match.match_id}>
                    <td>{match.match_start}</td>
                    <td>{match.match_end}</td>
                    <td>{match.match_p1}</td>
                    <td>{match.match_p2}</td>
                    <td>{match.match_winner}</td>
                </tr>
            );
        }
        return rows;
    }

    renderRefreshButton() {
        return (
            <div className="refresh-button">
                <Button color="primary" onClick={() => this.sendMatchHistoryRequest()}>Refresh</Button>
            </div>
        );
    }

    async sendMatchHistoryRequest() {
        const matchHistoryResponse = await sendAPIRequest({ requestType: "matchHistory" , user_id: this.user_id}, this.serverUrl);

        if (matchHistoryResponse) {
            this.updateMatchHistoryList(matchHistoryResponse);
            console.log(`MatchHistory request to ${this.serverUrl} was successful.`);
        } else {
            console.log(`MatchHistory request to ${this.serverUrl} failed. Check the log for more details.`);
        }
    }

    updateMatchHistoryList(matchHistoryResponse) {
        let matchHistory = [];
        for (let match of matchHistoryResponse.matches) {
            matchHistory.push({
                match_id: match.match_id,
                match_start: match.match_start,
                match_end: match.match_end,
                match_p1: match.match_p1,
                match_p2: match.match_p2,
                match_winner: match.match_winner
            });
        }
        this.setState({matchHistoryArray: matchHistory});
    }
}