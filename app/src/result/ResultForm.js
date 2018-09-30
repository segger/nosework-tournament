import React, { Component } from 'react';
import {
    UncontrolledCollapse,
    Form, FormGroup, Label, Col, Input, Button } from 'reactstrap';

class ResultForm extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        console.log('ResultForm - componentDidMount');
    }

    render() {

        const events = ['one', 'two', 'three'];

        const protocol = events.map(event => {
            return (
                <div key={event}>
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

                </div>
            )
        });

        return (
            <div>
                <Button color="primary" id="toggler" block>
                Lägg till
                </Button>

                <UncontrolledCollapse toggler="#toggler">
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