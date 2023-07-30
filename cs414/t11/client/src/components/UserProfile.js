import {
    Button,
    Col,
    Modal,
    ModalBody,
    ModalFooter,
    ModalHeader,
    Row,
    TabPane
} from "reactstrap";
import React, {useState} from "react";
import MatchHistory from "./MatchHistory";

export default function UserProfile(props) {

    const [displayInviteModal, setDisplayInviteModal] = useState(false);
    const toggleInviteModal = () => setDisplayInviteModal(!displayInviteModal);
    const [displayUnregisterModal, setDisplayUnregisterModal] = useState(false);
    const toggleUnregisterModal = () => setDisplayUnregisterModal(!displayUnregisterModal);

    return (
        <TabPane tabId="1">
            <Row>
                <Col>
                    <h4 className="page-header">{props.user_id}</h4>
                </Col>
            </Row>
            <Row>
                {renderNewMatchButton(toggleInviteModal, displayInviteModal)}
                {renderUnregisterButton(props, toggleUnregisterModal, displayUnregisterModal)}
            </Row>
            <MatchHistory serverUrl={props.serverUrl} user_id={props.user_id}/>
        </TabPane>
    );
}

function renderNewMatchButton(toggleInviteModal, displayInviteModal) {
    return (
        <Col className="new-match-button">
            <Button color="primary" onClick={toggleInviteModal}>Create New Match</Button>
            <DisplayInviteModal toggleInviteModal={toggleInviteModal}
                                displayInviteModal={displayInviteModal}/>
        </Col>
    );
}

function renderUnregisterButton(props, toggleUnregisterModal, displayUnregisterModal) {
    return (
        <Col className="unregister-button">
            <Button color="primary" onClick={toggleUnregisterModal}>Unregister</Button>
            <DisplayUnregisterModal toggleUnregisterModal={toggleUnregisterModal}
                                    displayUnregisterModal={displayUnregisterModal}
                                    toggleButtons={props.toggleButtons}/>
        </Col>
    );
}

function DisplayInviteModal(props) {

    CreateNewMatch();

    return (
        <Modal isOpen={props.displayInviteModal}>
            <ModalHeader>
                Invite Players
            </ModalHeader>
            <ModalBody>
                <div>
                    <label>Nickname&nbsp;</label>
                    <input placeholder="Enter Nickname ..."/>
                </div>
            </ModalBody>
            <ModalFooter>
                <Button color="primary" onClick={sendInvite()}>
                    Send Invite
                </Button>
                <Button color="primary" onClick={props.toggleInviteModal}>
                    Close
                </Button>
            </ModalFooter>
        </Modal>
    );
}

function CreateNewMatch() {
    // TODO: Create a new match in the database.
}

function sendInvite() {
    // TODO: Send an invite to specified user
}

function DisplayUnregisterModal(props) {
    return (
        <Modal isOpen={props.displayUnregisterModal}>
            <ModalHeader>
                Unregister
            </ModalHeader>
            <ModalBody>
                <Row>
                    <Col>Are you sure you want to unregister? Your account will be permanently deleted.</Col>
                </Row>
            </ModalBody>
            <ModalFooter>
                <Button color="primary" onClick={props.toggleUnregisterModal}>
                    Cancel
                </Button>
                <Button color="danger" onClick={props.toggleButtons}>
                    Unregister
                </Button>
            </ModalFooter>
        </Modal>
    );
}
