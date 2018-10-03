import React, { Component } from 'react';

class ResultNavigation extends Component {

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