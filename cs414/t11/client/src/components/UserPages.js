import React, {useState} from 'react';
import {
	Nav,
	NavItem,
	NavLink,
	TabContent
} from 'reactstrap';
import UserProfile from './UserProfile';
import CurrentMatch from './CurrentMatch';
import Match from './Match'
import OngoingMatches from "./OngoingMatches";

export default function DisplayUserPages(props) {

	const [activeTab, setActiveTab] = useState('1');

	let matchesJson = '{' +
		' "match_1": {"match_id":1, "match_p1":"apple", "match_2":"banana", "match_turn": "apple", "match_board": "8/8/4k3/8/8/8/8/8", "match_active": false, "match_winner": "apple", "match_start": "01/01/21-0:00", "match_end": "01/01/21-16:20"},' +
		' "match_2": {"match_id":2, "match_p1":"banana", "match_p2": "apple", "match_turn": "apple", "match_board": "rnbqkbnr/pppp1ppp/8/4p3/8/8/PPPPPPPP/RNBQKBNR", "match_active": true, "match_winner": "", "match_start": "01/02/21-13:00", "match_end": null} }';

	let matchesTest = new Match(matchesJson, props.serverUrl);
  	return (
    	<div>
			<Nav tabs justified pills>
				<NavItem>
					<NavLink className={activeTab === '1' ? 'active' : ''} onClick={() => setActiveTab('1')}>
						Profile
					</NavLink>
				</NavItem>
				<NavItem>
					<NavLink className={activeTab === '2' ? 'active' : ''} onClick={() => setActiveTab('2')}>
						Ongoing Matches
					</NavLink>
				</NavItem>
				<NavItem>
					<NavLink className={activeTab === '3' ? 'active' : ''} onClick={() => setActiveTab('3')}>
						Current Match
					</NavLink>
				</NavItem>
			</Nav>
			<TabContent activeTab={activeTab}>
				<UserProfile user_id={props.user_id} serverUrl={props.serverUrl} toggleButtons={props.toggleButtons} matchesObj={matchesTest}/>
				<OngoingMatches serverUrl={props.serverUrl}/>
				<CurrentMatch matchesObj={matchesTest} />
			</TabContent>
		</div>
  	);
}
