import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListComponents from "./component/user/ListComponents";

//File to specify the routers for our application

function App() {
  return (
      <div className="container">
          <Router>
              <div className="col-md-6">
                  <h1 className="text-center" style={style}>React User Application</h1>
                  <Switch>
                      <Route path="/" exact component={ListComponents} />
                      <Route path="/hiveoutput" component={ListComponents} />
                  </Switch>
              </div>
          </Router>
      </div>
  );
}

const style = {
    color: 'red',
    margin: '10px'
}

export default App;
