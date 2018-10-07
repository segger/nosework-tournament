import React from 'react'
import { Switch, Route } from 'react-router-dom'
import ResultPage from '../result/ResultPage';
import TournamentPage from '../tournament/TournamentPage';

const MainRouter = () => (
    <Switch>
        <Route exact path="/" component={TournamentPage}/>
        <Route path="/result" component={ResultPage}/>
        <Route path="/tournaments" component={TournamentPage}/>
    </Switch>
)

export default MainRouter;