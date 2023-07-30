import React, { useEffect, useState } from 'react';
import { Collapse, Button} from 'reactstrap';
import Header from './Margins/Header';
import Footer from './Margins/Footer';
import About from './About/About';
import DisplayLoginRegistration from './Login';
import DisplayUserPages from './UserPages';
import { useToggle } from '../hooks/useToggle';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';

export default function Page(props) {
	const [showAbout, toggleAbout] = useToggle(false);
	const [serverSettings, processServerConfigSuccess] = useServerSettings(props.showMessage);

	return (
		<>
			<Header toggleAbout={toggleAbout} />
			<div className="body">
				<Collapse isOpen={showAbout}>
					<About closePage={toggleAbout} />
				</Collapse>
			</div>
			{renderLogin(serverSettings)}
			<Footer
				serverSettings={serverSettings}
				processServerConfigSuccess={processServerConfigSuccess}
			/>
		</>
	)
}

function renderLogin(serverSettings){
	const[show,setShow]=useState(true);
	const toggleButtons = () => setShow(!show);
	
	const [displayLogin, setDisplayLogin] = useState(false);
  	const toggleLogin = () => setDisplayLogin(!displayLogin);

	const [displayRegistration, setDisplayRegistration] = useState(false);
  	const toggleRegistration = () => setDisplayRegistration(!displayRegistration);

	// TODO: Populate user_id with username entered by user
	const user_id = "playerOne";

	if(show){
		return(
			<div>
				<h1 className="login-header">Extinction Chess</h1>
				<DisplayLoginRegistration displayRegistration={displayRegistration} toggleRegistration={toggleRegistration}
									  displayLogin={displayLogin} toggleLogin={toggleLogin}
									  toggleButtons={toggleButtons} setShow={setShow} show={show}
			/>
			</div>
		)
	}
	else{
		return(
			<div>
				<DisplayUserPages user_id={user_id} serverUrl={serverSettings.serverUrl} toggleButtons={toggleButtons}/>
				<div className="logout-button">
					<Button color="warning" onClick={toggleButtons}>Log out</Button>
				</div>
			</div>
		)
	}
}

function useServerSettings(showMessage) {
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [serverConfig, setServerConfig] = useState(null);

	useEffect(() => {
		sendConfigRequest();
	}, []);

	function processServerConfigSuccess(config, url) {
		LOG.info("Switching to Server:", url);
		setServerConfig(config);
		setServerUrl(url);
	}

	async function sendConfigRequest() {
		const configResponse = await sendAPIRequest({ requestType: "config" }, serverUrl);
		if (configResponse) {
			processServerConfigSuccess(configResponse, serverUrl);
		} else {
			setServerConfig(null);
			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, "error");
		}
	}

	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess];
}
