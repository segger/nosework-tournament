import React, { Component } from 'react';
import TournamentList from "./TournamentList";

class TournamentPage extends Component {
    componentDidMount() {
        console.log('TournamentPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Tuneringar</h3>
                <TournamentList />
            </div>
        );
    }
}

export default TournamentPage;