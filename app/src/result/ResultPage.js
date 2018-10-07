import React, { Component } from 'react';
import ResultNavigation from "./ResultNavigation";
import ResultForm from "./ResultForm";
import ResultList from "./ResultList";

class ResultPage extends Component {
    constructor(props) {
        super(props);

        console.log('path id: ' + JSON.stringify(props.match.params.id));

        this.state = {
            tournamentId: props.match.params.id
        }
    }

    componentDidMount() {
        console.log('ResultPage - componentDidMount');
    }

    render() {
        return (
            <div className="Page">
                <h3 className="Page-header">Resultat</h3>
                <ResultNavigation />
                <ResultForm tournamentId={this.state.tournamentId} />
                <ResultList tournamentId={this.state.tournamentId} />
            </div>
        );
    }
}

export default ResultPage;