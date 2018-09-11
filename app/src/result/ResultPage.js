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
                <ResultForm />
                <ResultList />
            </div>
        );
    }
}

export default ResultPage;