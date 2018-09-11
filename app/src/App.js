import React, {Component} from 'react';
import './App.css';
import TournamentList from './tournament/TournamentList';
import Menu from './menu/Menu';

class App extends Component {
    state = {
        isLoading: true,
        tournaments: []
    };

    async componentDidMount() {
        console.log('App.js - componentDidMount');
    }

    render() {
        return (
            <div className="App">
                <div className="App-header">
                    <div className="App-intro">
                        <div className="App-title">Nosework Tournament App</div>
                        <span>by segger</span>
                    </div>
                </div>
                <Menu />

                <TournamentList />
            </div>
        );
    }
}

export default App;
