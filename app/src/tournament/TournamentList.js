import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

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
                    <td>
                        EDIT
                    </td>
                </tr>
            )
        });

        return (
               <div>
                   <Container fluid>
                       <div className="float-right">
                           ADD
                       </div>
                       <h3>Tournaments</h3>
                       <Table className="mt-4">
                           <thead>
                           <tr>
                               <th>Namn</th>
                               <th width="10%">Actions</th>
                           </tr>
                           </thead>
                           <tbody>
                           {tournamentList}
                           </tbody>
                       </Table>
                   </Container>
               </div>
        );
    }
}

export default TournamentList;