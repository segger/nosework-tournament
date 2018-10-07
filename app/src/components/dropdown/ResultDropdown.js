import React, { Component } from 'react';
import { UncontrolledButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

class ResultDropdown extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        console.log('ResultDropdown - componentDidMount');
    }

    render() {
        return (
            <UncontrolledButtonDropdown>
                <DropdownToggle caret>
                    Contest
                </DropdownToggle>
                <DropdownMenu>
                    <DropdownItem>Contest 1</DropdownItem>
                    <DropdownItem>contest 2</DropdownItem>
                </DropdownMenu>
            </UncontrolledButtonDropdown>
        );
    }
}

export default ResultDropdown;