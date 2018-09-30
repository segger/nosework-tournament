import React, { Component } from 'react';
import ResultNavigation from "./ResultNavigation";
import ResultForm from "./ResultForm";
import ResultList from "./ResultList";

class ResultPage extends Component {
    componentDidMount() {
        console.log('ResultPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Resultat</h3>
                <ResultNavigation />
                <ResultForm />
                <ResultList />
            </div>
        );
    }
}

export default ResultPage;