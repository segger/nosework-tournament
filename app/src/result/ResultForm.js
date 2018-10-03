import React, { Component } from 'react';
import {
    UncontrolledCollapse, Collapse,
    Form, FormGroup, Label, Col, Input, Button } from 'reactstrap';

class ResultForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            events: []
        };
    }
    componentDidMount() {
        console.log('ResultForm - componentDidMount');

        const mockEvents = [{"id":0, "name": "one", "collapsed": false}, {"id":1, "name": "one1", "collapsed": false}];
        this.setState({
            events: mockEvents
        })
    }

    onClickEvent = (id) => {
        console.log('onClickEvent: ' + id);
        let collapsedEvents = this.state.events;

        for(let i = 0; i < collapsedEvents.length; i++) {
            if(i === id) {
                collapsedEvents[i].collapsed = !collapsedEvents[i].collapsed;
            } else {
                collapsedEvents[i].collapsed = false;
            }
        }

        this.setState({
            events: collapsedEvents
        });
    };

    render() {
        const { events } = this.state;

        const protocol = events.map(event => {
            return (
                <div key={event.id}>
                <Button color="secondary" onClick={(id) => this.onClickEvent(event.id)} block>Sök {event.id}</Button>
                <Collapse isOpen={event.collapsed}>
                    <FormGroup row>
                        <Label for="comment" sm={2}>Kommentar</Label>
                        <Col sm={10}>
                            <Input type="textarea" name="comment" id="comment"/>
                        </Col>
                    </FormGroup>

                    <FormGroup row>
                        <Label for="time" sm={2}>Tid</Label>
                        <Col sm={10}>
                            <Input type="time" name="time" id="time" placeholder="00:00" />
                        </Col>
                    </FormGroup>

                    <FormGroup check inline>
                        <Label check>
                            <Input type="checkbox" /> SSE
                        </Label>
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

                    <FormGroup check row>
                        <Col sm={{size: 10, offset: 2}}>
                            <Button>Spara</Button>
                        </Col>
                    </FormGroup>
                </Form>
                </UncontrolledCollapse>
            </div>
        );
    }
}

export default ResultForm;