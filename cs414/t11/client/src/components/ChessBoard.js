import { Chessboard } from 'react-chessboard';
import React from 'react';

export default function DisplayChessBoard(props) {
    // TODO: Change position when matches is implemented
    return(
        <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <Chessboard
                boardWidth={400}
                position={props.matches.match_2.match_board}
            />
        </div>
    );
};