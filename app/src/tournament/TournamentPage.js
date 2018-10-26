import React, { Component } from 'react';

class TournamentPage extends Component {
    componentDidMount() {
        console.log('TournamentPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Tuneringar</h3>
            </div>
        );
    }
}

export default TournamentPage;