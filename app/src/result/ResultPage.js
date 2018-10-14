import React, { Component } from 'react';
import * as queryString from 'query-string';
import ResultNavigation from "./ResultNavigation";
import ResultForm from "./ResultForm";
import ResultList from "./ResultList";

class ResultPage extends Component {
    constructor(props) {
        super(props);

        const queryParams = queryString.parse(props.location.search);
        let type = queryParams.type;

        this.state = {
            type: type,
            tournamentId: props.match.params.id,
            showForm: 'contest' === type.toLowerCase()
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
                <ResultNavigation tournamentId={this.state.tournamentId} type={this.state.type} />
                { showForm &&
                    <ResultForm tournamentId={this.state.tournamentId} type={this.state.type} />
                }
                <ResultList tournamentId={this.state.tournamentId} type={this.state.type}/>
            </div>
        );
    }
}

export default ResultPage;