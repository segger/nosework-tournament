import React, {Component} from 'react';
import './App.css';
import Menu from './menu/Menu';
import MainRouter from './router/MainRouter';

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
                    <Menu />
                </div>

                <MainRouter />
            </div>
        );
    }
}

export default App;
