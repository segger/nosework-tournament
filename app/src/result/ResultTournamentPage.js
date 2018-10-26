import React, { Component } from 'react';
import TournamentCards from "../tournament/TournamentCards";

class ResultTournamentPage extends Component {
    componentDidMount() {
        console.log('ResultTournamentPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Resultat</h3>
                <TournamentCards/>
            </div>
        );
    }
}

export default ResultTournamentPage;