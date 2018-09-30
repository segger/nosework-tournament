import React, { Component } from 'react';

class ResultNavigation extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        console.log('ResultNavigation - componentDidMount');
    }

    render() {

        return (
            <div className="tournamentNavigation">
                > Adventsnosen > Deltävling 2
            </div>
        )
    }
}

export default ResultNavigation;