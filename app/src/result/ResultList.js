import React, { Component } from 'react';
import { Table } from 'reactstrap';

class ResultList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            results: [],
            isLoading: true,
            tournamentId: props.tournamentId
        };
    }

    componentDidMount() {
        console.log('ResultList - componentDidMount');
        this.setState({isLoading: true});

        let url = '/api/results/' + this.state.tournamentId + '?type=tournament';
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({
                results: data,
                isLoading: false
            }));
    }

    render() {
        const { results, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading</p>
        }

        const resultList = results.map(result => {
            return (
                <tr key={result.id}>
                    <td>{result.placement}</td>
                    <td>{result.participant.presentation}</td>
                    <td>{result.points}</td>
                </tr>
            )
        });

        return (
               <div className="ResultList StripedList">
                   <Table striped bordered className="mt-4">
                       <thead>
                       <tr>
                           <th>Placering</th>
                           <th>Ekipage</th>
                           <th>Poäng</th>
                       </tr>
                       </thead>
                       <tbody>
                       {resultList}
                       </tbody>
                   </Table>
               </div>
        );
    }
}

export default ResultList;