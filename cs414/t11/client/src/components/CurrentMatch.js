import {Col, Row, TabPane} from "reactstrap";
import DisplayChessBoard from "./ChessBoard";
import React from "react";

export default function CurrentMatch(props) {
    // TODO: Change header text when matches are implemented
    console.log("CurrentMatch: ", props.matchesObj);
    return (
        <TabPane tabId="3">
            <Row>
                <Col>
                    <h4 className="page-header">Match: match_id</h4>
                </Col>
            </Row>
            <Row>
                <Col>
                    <h4 className="page-header">Turn: {props.matchesObj.matches.match_2.match_turn}</h4>
                </Col>
            </Row>
            <Row>
                <Col>
                    <DisplayChessBoard matches={props.matchesObj.matches} />
                </Col>
            </Row>
        </TabPane>
    );
}
