import React, { Component } from 'react';

import {
    Collapse, Form, FormGroup, Label, Col, Input, Button } from 'reactstrap';
import './ResultForm.css';
import EventProtocolForm from "./EventProtocolForm";

class ResultForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            formData: {
                participant: '',
                protocols: new Map()
            },
            events: [],
            formOpen: false
        };
    }
    componentDidMount() {
        console.log('ResultForm - componentDidMount');

        fetch('/api/contests/1/events')
            .then(response => response.json())
            .then(data => {
                let events = [];
                data._embedded.events.forEach(item => {
                    let event = this.transform(item);
                    events.push(event);
                });

                events.sort((a, b) => { return a.order - b.order });

                this.setState({
                    events: events
                });
            });
    }

    transform = (item) => {
        return {
            id: '_' + Math.random().toString(36).substr(2, 9),
            name: item.name,
            type: item.type,
            maxTime: item.maxTime,
            order: item.contestOrder,
            collapsed: false
        }
    };

    toggleForm = () => {
        this.setState({
            formOpen: !this.state.formOpen
        })
    };

    onClickEvent = (id) => {
        let collapsedEvents = this.state.events;

        collapsedEvents.forEach(item => {
            if(item.id === id) {
                item.collapsed = !item.collapsed;
            } else {
                item.collapsed = false;
            }
        });

        this.setState({
            events: collapsedEvents
        });
    };

    onUpdateProtocol = (protocol) => {
        let id = protocol.id;
        let protocolList = this.state.formData.protocols;
        protocolList.set(id, protocol);

        this.setState({
            formData: {
                ...this.state.formData,
                protocols: protocolList
            }
        });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        this.toggleForm();

        let protocolList = [];
        this.state.formData.protocols.forEach(item => {
            let protocol = {
                time: item.timeMin + ":" + item.timeSec,
                sse: item.sse,
                errorPoints: item.errorPoints
            };
            protocolList.push(protocol);
        });

        fetch('/api/protocols/list', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(protocolList)
        });
    };

    render() {
        const { events } = this.state;

        const protocol = events.map(event => {
            return (
                <div key={event.id}>
                <Button color="secondary" onClick={(id) => this.onClickEvent(event.id)} block>
                    <span className="event-header-name">{event.name}</span> <span className="event-header-maxTime">{event.maxTime}</span>
                </Button>
                <Collapse isOpen={event.collapsed}>
                    <EventProtocolForm {...event} updateProtocol={this.onUpdateProtocol}/>
                </Collapse>
                </div>
            );
        });

        return (
            <div>
                <Button color="primary" onClick={this.toggleForm} block>
                Lägg till
                </Button>

                <Collapse isOpen={this.state.formOpen}>
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup row>
                        <Label for="participant" sm={2}>Deltagare</Label>
                        <Col sm={10}>
                            <Input
                                type="text"
                                name="participant"
                                id="participant"
                                placeholder="Deltagare"
                                value={this.state.formData.participant}
                                onChange={(e) => this.setState({
                                    formData: {
                                        ...this.state.formData,
                                        participant: e.target.value
                                    }
                                })} />
                        </Col>
                    </FormGroup>

                    {protocol}

                    <FormGroup row>
                        <Col>
                            <Button color="success" block>Spara</Button>
                        </Col>
                    </FormGroup>
                </Form>
                </Collapse>
            </div>
        );
    }
}

export default ResultForm;