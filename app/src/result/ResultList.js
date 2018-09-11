import React, { Component } from 'react';
import { Table } from 'reactstrap';

class ResultList extends Component {
    constructor(props) {
        super(props);
        this.state = { results: [], isLoading: true };
    }

    componentDidMount() {
        console.log('ResultList - componentDidMount');
        this.setState({isLoading: false});
    }

    render() {
        const { results, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading</p>
        }

        return (
               <div className="TournamentList">
                   <h3>Resultat</h3>
                   <Table striped bordered className="mt-4">
                       <thead>
                       <tr>
                           <th>Namn</th>
                           <th width="10%"></th>
                       </tr>
                       </thead>
                       <tbody>
                       <tr><td colSpan={2}>
                        Inga resultat ännu.
                       </td></tr>
                       </tbody>
                   </Table>
               </div>
        );
    }
}

export default ResultList;