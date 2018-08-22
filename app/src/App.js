import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import { Table } from 'react-bootstrap';
import TournamentList from './tournament/TournamentList';

class App extends Component {
    state = {
        isLoading: true,
        tournaments: []
    };

    async componentDidMount() {
        const response = await fetch('/api/tournaments')
        const body = await response.json();
        console.log(body._embedded.tournaments);
        const list = body._embedded.tournaments;
        this.setState({tournaments: list, isLoading: false});
    }

    render() {
        const {tournaments, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        return (
            <div className="App">
                <div className="App-intro">
                    <h2>List of tournaments</h2>
                    {tournaments.map(tournament =>
                        <div key={tournament.name}>
                            {tournament.name}
                        </div>
                    )}
                </div>

                <TournamentList />

                <Table striped bordered condensed hover>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Namn</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Sommarsniffen</td>
                    </tr>
                    </tbody>
                </Table>

            </div>
        );
    }
}

export default App;
