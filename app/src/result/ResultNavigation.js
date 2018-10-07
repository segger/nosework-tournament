import React, { Component } from 'react';
import { Breadcrumb, BreadcrumbItem } from 'reactstrap';

class ResultNavigation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            tournamentId: props.tournamentId,
            tournament: {}
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
        const { tournament } = this.state;

        return (
            <div className="tournamentNavigation">
                <Breadcrumb tag="nav">
                    <BreadcrumbItem active tag="a" href="#"> { tournament.name }</BreadcrumbItem>
                </Breadcrumb>
            </div>
        )
    }
}

export default ResultNavigation;