import React, { Component } from 'react';
import { Table } from 'reactstrap';

class TournamentList extends Component {
    constructor(props) {
        super(props);
        this.state = { tournaments: [], isLoading: true };
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/api/tournaments')
            .then(response => response.json())
            .then(data => this.setState({ tournaments: data._embedded.tournaments, isLoading: false}));
    }

    render() {
        const { tournaments, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading</p>
        }

        const tournamentList = tournaments.map(tournament => {
            return (
                <tr key={tournament.name}>
                    <td>{tournament.name}</td>
                    <td>{tournament.level}</td>
                </tr>
            )
        });

        return (
               <div className="TournamentList StripedList">
                   <Table striped bordered className="mt-4">
                       <thead>
                       <tr>
                           <th>Namn</th>
                           <th>Klass</th>
                       </tr>
                       </thead>
                       <tbody>
                       {tournamentList}
                       </tbody>
                   </Table>
               </div>
        );
    }
}

export default TournamentList;