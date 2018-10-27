import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
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
            route: resultRoute,
            isLoading: false,
            contestSelected: false
        };
    }

    componentDidMount() {
        console.log('ResultNavigation - componentDidMount ' + this.state.tournamentId);
        this.setState({
            isLoading: true
        });

        let url = '/api/tournaments/' + this.state.tournamentId;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                    this.setState({
                        isLoading: false,
                        tournament: data
                    });
                }
            );
    }

    onSelectedContest = (contest) => {
        console.log('on selected contest: ' + contest.id);
        this.props.history.push("/results/" + contest.id + "?type=contest");

        this.setState({
            contest: contest,
            contestSelected: true
        })

    };

    render() {
        const { tournament, route, isLoading, contestSelected, contest } = this.state;

        if (isLoading) {
            return <p>Loading</p>
        }

        return (
            <div className="tournamentNavigation">
                <Breadcrumb tag="nav">
                    { !contestSelected &&
                    <BreadcrumbItem className="button-align" active tag="a" href={route + "tournament"}> { tournament.name }</BreadcrumbItem>
                    }
                    { contestSelected &&
                    <BreadcrumbItem className="button-align" tag="a"
                                    href={route + "tournament"}> {tournament.name}</BreadcrumbItem>
                    }
                    { contestSelected &&
                    <BreadcrumbItem className="button-align" active tag="a" href={route + "contest"}> { contest.name } </BreadcrumbItem>
                    }
                    <BreadcrumbItem>
                        <ResultDropdown data={tournament} onSelectedContest={this.onSelectedContest}/>
                    </BreadcrumbItem>
                </Breadcrumb>
            </div>
        )
    }
}

export default withRouter(ResultNavigation);