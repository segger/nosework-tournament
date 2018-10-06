import React, { Component } from 'react';
import { FormGroup, Label, Col, Input} from 'reactstrap';
import './ResultForm.css';

class EventProtocolForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: props.id,
            timeMin: 0,
            timeSec: 0,
            stash: false,
            errorPoints: 0,
            sse: false,
            comment: ''
        };
    }

    onFormChange = (e) => {
        let value = e.target.value;
        if ("checkbox" === e.target.type) {
            value = e.target.checked;
        } else if("number" === e.target.type) {
            value = e.target.value;
        }
        this.setState({
            [e.target.name]: value
        }, () => { this.props.updateProtocol(this.state) });
    };

    render() {
        return (
            <div>
            <FormGroup row className="time-row">
                <Col className="form-col">
                    <Label className="form-label">Tid</Label>
                </Col>
                <Col className="time-input">
                    <Input
                        type="number"
                        name="timeMin"
                        id="timeMin"
                        value={this.state.timeMin}
                        onChange={(e) => this.onFormChange(e)}
                        placeholder="00"
                        className="time-input"/>:
                    <Input
                        type="number"
                        name="timeSec"
                        id="timeSec"
                        value={this.state.timeSec}
                        onChange={(e) => this.onFormChange(e)}
                        placeholder="00"
                        className="time-input" />
                </Col>
            </FormGroup>

            <FormGroup row>
                <Col className="form-col">
                    <Label className="stash-label">Fönster</Label>
                        <div className="points-label">(25p)</div>
                </Col>
                <Col>
                    <Input
                        type="checkbox"
                        name="stash"
                        id="stash"
                        value={this.state.stash}
                        onChange={(e) => this.onFormChange(e)} />
                </Col>
            </FormGroup>

            <FormGroup row>
                <Col className="form-col">
                    <Label className="form-label">Felpoäng</Label>
                </Col>
                <Col>
                    <Input
                        type="number"
                        name="errorPoints"
                        id="errorPoints"
                        placeholder="0"
                        value={this.state.errorPoints}
                        onChange={(e) => this.onFormChange(e)} />
                </Col>
            </FormGroup>

            <FormGroup row>
                <Col className="form-col">
                    <Label>SSE</Label>
                </Col>
                <Col>
                    <Input
                        type="checkbox"
                        name="sse"
                        id="sse"
                        value={this.state.sse}
                        onChange={(e) => this.onFormChange(e)} />
                </Col>
            </FormGroup>

            <FormGroup row>
                <Label for="comment" sm={1}>Kommentar</Label>
                <Col sm={10}>
                    <Input
                        type="textarea"
                        name="comment"
                        id="comment"
                        value={this.state.comment}
                        onChange={(e) => this.onFormChange(e)} />
                </Col>
            </FormGroup>
            </div>
        );
    }
}

export default EventProtocolForm;