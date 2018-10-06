import React, { Component } from 'react';

import {
    UncontrolledCollapse, Collapse,
    Form, FormGroup, Label, Col, Input, Button } from 'reactstrap';
import './ResultForm.css';

class ResultForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            events: []
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
            id: item.name + Math.random().toString(36).substr(2, 9),
            name: item.name,
            order: item.contestOrder,
            collapsed: false
        }
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

    render() {
        const { events } = this.state;

        const protocol = events.map(event => {
            return (
                <div key={event.id}>
                <Button color="secondary" onClick={(id) => this.onClickEvent(event.id)} block>{event.name}</Button>
                <Collapse isOpen={event.collapsed}>
                    <FormGroup row className="time-row">
                        <Col className="form-col">
                            <Label className="form-label">Tid</Label>
                        </Col>
                        <Col className="time-input">
                            <Input type="number" name="timeMin" id="timeMin" placeholder="00" className="time-input"/>:
                            <Input type="number" name="timeSec" id="timeSec" placeholder="00" className="time-input"/>
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Col className="form-col">
                            <Label className="stash-label">Fönster</Label>
                            <div className="points-label">(25p)</div>
                        </Col>
                        <Col>
                            <Input type="checkbox" />
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Col className="form-col">
                            <Label className="form-label">Felpoäng</Label>
                        </Col>
                        <Col>
                            <Input type="number" name="errorPoints" id="errorPoints" placeholder="0"/>
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Col className="form-col">
                            <Label>SSE</Label>
                        </Col>
                        <Col>
                            <Input type="checkbox" />
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Label for="comment" sm={1}>Kommentar</Label>
                        <Col sm={10}>
                            <Input type="textarea" name="comment" id="comment"/>
                        </Col>
                    </FormGroup>
                </Collapse>
                </div>
            );
        });

        return (
            <div>
                <Button color="primary" id="mainToggler" block>
                Lägg till
                </Button>

                <UncontrolledCollapse toggler="#mainToggler">
                <Form>
                    <FormGroup row>
                        <Label for="participant" sm={2}>Deltagare</Label>
                        <Col sm={10}>
                            <Input type="text" name="participant" id="participant" placeholder="Deltagare"/>
                        </Col>
                    </FormGroup>

                    {protocol}

                    <FormGroup row>
                        <Col>
                            <Button color="success" block>Spara</Button>
                        </Col>
                    </FormGroup>
                </Form>
                </UncontrolledCollapse>
            </div>
        );
    }
}

export default ResultForm;