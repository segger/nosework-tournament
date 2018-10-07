import React from 'react'
import { Switch, Route } from 'react-router-dom'
import ResultPage from '../result/ResultPage';
import TournamentPage from '../tournament/TournamentPage';

const MainRouter = () => (
    <Switch>
        <Route exact path="/" component={TournamentPage}/>
        <Route exact path="/results" component={TournamentPage} />
        <Route path="/results/:id" component={ResultPage}/>
    </Switch>
)

export default MainRouter;