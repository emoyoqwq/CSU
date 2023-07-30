import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';


export default function DisplayLoginRegistration(props) {
	
  	return (
    <div>
		<div className="login-button">
			<Button className="login-button" color="primary" onClick={props.toggleLogin}>Login</Button>
		</div>
		<div className="register-button">
			<Button color="primary" onClick={props.toggleRegistration}>Register</Button>
		</div>
		<Modal isOpen={props.displayLogin}>
        	<ModalHeader >
          		Login
        	</ModalHeader>
        	<ModalBody>
				<div>
					<label>Email&nbsp;</label>
					<input placeholder="Enter email"/>
				</div>
				<div>
					<label>Password&nbsp;</label>
					<input placeholder="Enter Password"/>
				</div>
			</ModalBody>
        	<ModalFooter>
				<Button color="primary" onClick={props.toggleLogin}>
					Cancel
				</Button>
				<Button color="primary" onClick={() => logUserIntoPlatform(props.show, props.setShow)}>
					Login
				</Button>
        	</ModalFooter>
      	</Modal>
	
      	<Modal isOpen={props.displayRegistration}>
        	<ModalHeader>
          		Register
        	</ModalHeader>
        	<ModalBody>
				<div>
					<label>Email&nbsp;</label>
					<input placeholder="Enter email"/>
				</div>
				<div>
					<label>Nickname&nbsp;</label>
					<input placeholder="Enter nickname"/>
				</div>
				<div>
					<label>Password&nbsp;</label>
					<input placeholder="Enter Password"/>
				</div>
			</ModalBody>
        	<ModalFooter>
				<Button color="primary" onClick={props.toggleRegistration}>
					Cancel
				</Button>
				<Button color="primary" onClick={props.toggleRegistration}>
					Register
				</Button>
        	</ModalFooter>
      	</Modal>
    </div>
  	);
}

function logUserIntoPlatform(show, setShow) {
	setShow(!show);
}
