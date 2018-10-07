import React, { Component } from 'react';
import ResultNavigation from "./ResultNavigation";
import ResultForm from "./ResultForm";
import ResultList from "./ResultList";

class ResultPage extends Component {
    constructor(props) {
        super(props);

        console.log('ResultPage.match.params.id: ' + JSON.stringify(props.match.params.id));

        this.state = {
            id: props.match.params.id,
            showForm: false
        }
    }

    componentDidMount() {
        console.log('ResultPage - componentDidMount');
    }

    render() {
        const { showForm } = this.state;

        return (
            <div className="Page">
                <h3 className="Page-header">Resultat</h3>
                <ResultNavigation tournamentId={this.state.id} />
                { showForm &&
                    <ResultForm tournamentId={this.state.id} />
                }
                <ResultList tournamentId={this.state.id} />
            </div>
        );
    }
}

export default ResultPage;