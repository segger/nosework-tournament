import React, { Component } from 'react';
import { Breadcrumb, BreadcrumbItem } from 'reactstrap';
import ResultDropdown from '../components/dropdown/ResultDropdown';

class ResultNavigation extends Component {
    constructor(props) {
        super(props);

        let route = '/results/' + props.tournamentId;

        this.state = {
            tournamentId: props.tournamentId,
            tournament: {},
            route: route
        };
    }

    componentDidMount() {
        console.log('ResultNavigation - componentDidMount ' + this.state.tournamentId);

        let url = '/api/tournaments/' + this.state.tournamentId;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                    console.log('tournament: ' + JSON.stringify(data));
                    this.setState({
                        tournament: data
                    });
                }
            ).catch(() => {
                console.log('failure');
            });
    }

    render() {
        const { tournament, route } = this.state;

        return (
            <div className="tournamentNavigation">
                <Breadcrumb tag="nav">
                    <BreadcrumbItem className="button-align" active tag="a" href={route}> { tournament.name }</BreadcrumbItem>
                    <BreadcrumbItem ><ResultDropdown /></BreadcrumbItem>
                </Breadcrumb>
            </div>
        )
    }
}

export default ResultNavigation;