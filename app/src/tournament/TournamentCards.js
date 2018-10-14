import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Card, CardTitle } from 'reactstrap';

import './TournamentCards.css';

class TournamentCards extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tournaments: []
        }
    }

    componentDidMount() {
        console.log('TournamentCards - componentDidMount');

        fetch('/api/tournaments')
            .then(response => response.json())
            .then(data => {
                    let tournaments = [];
                    data._embedded.tournaments.forEach(item => {
                        let tournament = this.transform(item);
                        tournaments.push(tournament);
                    });

                    this.setState({
                        tournaments: tournaments
                    });
                });
    }

    transformLevel = (level) => {
        switch(level) {
            case 'ONE': return "1";
            case 'TWO': return "2";
            case 'THREE': return "3";
            default: return "";
        }
    };

    transform = (item) => {
        return {
            id: item.id,
            name: item.name,
            level: this.transformLevel(item.level),
            color: item.color === undefined ? 'default' : item.color,
            link: item._links.self.href
        };
    };

    selectTournament = (id) => {
        //let tournament = this.state.tournaments.find((item) => { return item.id === id; });
        this.props.history.push("/results/" + id + "?type=tournament");
    };

    render() {
        const { tournaments } = this.state;

        const tournamentCards = tournaments.map(tournament => {
            return (
                <Card key={tournament.id} body inverse
                      onClick={() => this.selectTournament(tournament.id)}
                      className={"text-left TournamentCard " + tournament.color}>
                    <CardTitle className="tournamentTitle">
                        <span className="tournamentName">{tournament.name}</span>
                        <span className="tournamentLevel">Klass {tournament.level}</span>
                        </CardTitle>
                </Card>
            );
        });

        return (
            <div className="TournamentCards">
                {tournamentCards}
            </div>
        );
    }
}

export default withRouter(TournamentCards);