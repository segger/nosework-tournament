import React from 'react'
import { Switch, Route, Redirect } from 'react-router-dom'
import ResultPage from '../result/ResultPage';
import TournamentPage from '../tournament/TournamentPage';

const MainRouter = () => (
    <Switch>
        <Route exact path="/" component={ResultPage}/>
        <Route path="/result" component={ResultPage}/>
        <Route path="/tournaments" component={TournamentPage}/>
    </Switch>
)

export default MainRouter;