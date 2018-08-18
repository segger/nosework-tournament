import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

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
        this.setState({ tournaments: list, isLoading: false });
    }

  render() {
        const { tournaments, isLoading } = this.state;

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <div className="App-intro">
          <h2>List of tournaments</h2>
            { tournaments.map (tournament =>
                <div key={tournament.name}>
                    {tournament.name}
                </div>
            )}
        </div>
      </div>
    );
  }
}

export default App;
