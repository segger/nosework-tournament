import React, { Component } from 'react';
import { Breadcrumb, BreadcrumbItem } from 'reactstrap';
import ResultDropdown from '../components/dropdown/ResultDropdown';

class ResultNavigation extends Component {
    constructor(props) {
        super(props);

        let resultRoute = '/results/' + props.tournamentId + '?type=';
        // let type = props.type;

        this.state = {
            tournamentId: props.tournamentId,
            tournament: {},
            route: resultRoute
        };
    }

    componentDidMount() {
        console.log('ResultNavigation - componentDidMount ' + this.state.tournamentId);

        let url = '/api/tournaments/' + this.state.tournamentId;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                    this.setState({
                        tournament: data
                    });
                }
            );
    }

    render() {
        const { tournament, route } = this.state;

        return (
            <div className="tournamentNavigation">
                <Breadcrumb tag="nav">
                    <BreadcrumbItem className="button-align" active tag="a" href={route + "tournament"}> { tournament.name }</BreadcrumbItem>
                    <BreadcrumbItem>
                        <ResultDropdown data={tournament} />
                    </BreadcrumbItem>
                </Breadcrumb>
            </div>
        )
    }
}

export default ResultNavigation;