import React, { Component } from 'react';
import { sendAPIRequest } from '../utils/restfulAPI';
import {Button, Col, Row, Table, TabPane} from "reactstrap";

export default class OngoingMatches extends Component {

    constructor(props) {
        super(props);

        this.state = {
            ongoingMatchesArray: []
        }
        this.serverUrl = props.serverUrl;
    }

    render() {
        return (
            <TabPane tabId="2">
                <Row>
                    <Col>
                        <h4 className="page-header">Ongoing Matches</h4>
                    </Col>
                </Row>
                <Table>
                    <thead>
                    <tr>
                        <th>Match ID</th>
                        <th>Start Date/Time</th>
                        <th>Player 1</th>
                        <th>Player 2</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.renderOngoingMatchesTableRows()}
                    </tbody>
                </Table>
                {this.renderRefreshButton(this.serverUrl)}
            </TabPane>
        );
    }

    renderOngoingMatchesTableRows() {
        let rows = [];
        for (let match of this.state.ongoingMatchesArray) {
            rows.push(
                <tr key={match.match_id}>
                    <td>{match.match_id}</td>
                    <td>{match.match_start}</td>
                    <td>{match.match_p1}</td>
                    <td>{match.match_p2}</td>
                    <td><Button color="primary">Play</Button></td>
                </tr>
            );
        }
        return rows;
    }

    renderRefreshButton(serverUrl) {
        return (
            <div className="refresh-button">
                <Button color="primary" onClick={() => this.sendOngoingMatchesRequest(serverUrl)}>Refresh</Button>
            </div>
        );
    }

    async sendOngoingMatchesRequest() {
        const ongoingMatchesResponse = await sendAPIRequest({ requestType: "ongoingMatches" , user_id: "playerOne"}, this.serverUrl);

        if (ongoingMatchesResponse) {
            this.updateOngoingMatchesList(ongoingMatchesResponse);
            console.log(`OngoingMatches request to ${this.serverUrl} was successful.`);
        } else {
            console.log(`OngoingMatches request to ${this.serverUrl} failed. Check the log for more details.`);
        }
    }

    updateOngoingMatchesList(ongoingMatchesResponse) {
        let ongoingMatches = [];
        for (let match of ongoingMatchesResponse.matches) {
            ongoingMatches.push({
                match_id: match.match_id,
                match_start: match.match_start,
                match_p1: match.match_p1,
                match_p2: match.match_p2
            });
        }
        this.setState({ongoingMatchesArray: ongoingMatches});
    }
}
