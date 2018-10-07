import React, { Component } from 'react';
import TournamentCards from "./TournamentCards";

class TournamentPage extends Component {
    componentDidMount() {
        console.log('TournamentPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Tuneringar</h3>
                <TournamentCards/>
            </div>
        );
    }
}

export default TournamentPage;