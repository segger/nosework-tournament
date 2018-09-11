import React from 'react'
import { Switch, Route } from 'react-router-dom'
import ResultPage from '../result/ResultPage';
import TournamentList from '../tournament/TournamentList';

const MainRouter = () => (
    <Switch>
        <Route exact path='/' component={ResultPage}/>
        <Route path='/tournaments' component={TournamentList}/>
    </Switch>
)

export default MainRouter;