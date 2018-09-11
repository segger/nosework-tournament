import React from 'react'
import { Switch, Route } from 'react-router-dom'
import ResultList from '../result/ResultList';
import TournamentList from '../tournament/TournamentList';

const MainRouter = () => (
    <Switch>
        <Route exact path='/' component={ResultList}/>
        <Route path='/tournaments' component={TournamentList}/>
    </Switch>
)

export default MainRouter;