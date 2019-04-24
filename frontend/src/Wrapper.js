import React from "react";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import logo from './logo.svg';
import App from './App';
import './App.css';
import 'primereact/resources/themes/nova-colored/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import ToTable from './ToTable';
import FrontPage from './FrontPage';
import SingleBlog from './SingleBlog';
import { Redirect } from 'react-router';

function Index() {
    return <FrontPage />;
}

function Wrapper() {
  return (
    <Router>
      <div>
        <h1 className="logo">THIS IS ALWAYS</h1>
        <Switch>
            <Route path="/blogs/:blogId" component={SingleBlog} />
            <Route path="/blogs" component={Index} />
            <Route path="/" exact component={Index} />
        </Switch>
      </div>
    </Router>
  );
}

export default Wrapper;