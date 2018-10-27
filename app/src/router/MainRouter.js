import React from 'react'
import { Switch, Route } from 'react-router-dom'
import ResultTournamentPage from '../result/ResultTournamentPage';
import ResultPage from '../result/ResultPage';
import TournamentPage from '../tournament/TournamentPage';

const MainRouter = () => (
    <Switch>
        <Route exact path="/" component={ResultTournamentPage}/>
        <Route exact path="/results" component={ResultTournamentPage} />
        <Route path="/results/:id" component={ResultPage} />
        <Route exact path="/tournaments" component={TournamentPage} />
    </Switch>
)

export default MainRouter;