import React, { Component } from 'react';
import { UncontrolledButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

class ResultDropdown extends Component {
    constructor(props) {
        super(props);

        this.state = {
            tournamentId: props.data.id,
            contests: []
        }
    }

    componentDidMount() {
        console.log('ResultDropdown - componentDidMount ' + this.state.tournamentId);

        let url = '/api/tournaments/' + this.state.tournamentId + '/contests';

        if (this.state.tournamentId) {
            this.getContests(url);
        }
    }

    getContests = (url) => {
        fetch(url)
            .then(response => response.json())
            .then(data => {
                let contests = [];
                data._embedded.contests.forEach(item => {
                    let contest = this.transform(item);
                    contests.push(contest);
                });

                this.setState({
                    contests: contests
                })
            });
    };

    transform = (item) => {
        return {
            id: item.id,
            name: item.name,
            date: item.date
        };
    };

    selectContest = (contestId) => {
        this.state.contests.forEach((contest) => {
            if (contest.id === contestId) {
                this.props.onSelectedContest(contest);
            }
        });
    };

    render() {
        const { contests } = this.state;

        const dropdownContest = contests.map(contest => {
            return (
                <DropdownItem key={contest.id} onClick={() => this.selectContest(contest.id)}>{ contest.name } </DropdownItem>
            );
        });

        return (
            <UncontrolledButtonDropdown>
                <DropdownToggle caret>
                    Contests
                </DropdownToggle>
                <DropdownMenu>
                    { dropdownContest }
                </DropdownMenu>
            </UncontrolledButtonDropdown>
        );
    }
}

export default ResultDropdown;