import React, { Component } from 'react';
import ResultForm from "./ResultForm";
import ResultList from "./ResultList";

class ResultPage extends Component {
    componentDidMount() {
        console.log('ResultPage - componentDidMount');
    }

    render() {
        return (
            <div>
                <ResultList />
            </div>
        );
    }
}

export default ResultPage;