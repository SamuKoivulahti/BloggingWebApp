import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import logo from './logo.svg';
import App from './App';
import './App.css';
import 'primereact/resources/themes/nova-colored/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import ToTable from './ToTable';
import FrontPage from './FrontPage';
import { Redirect } from 'react-router';

function Index() {
    return <FrontPage />;
}

function Wrapper() {
  return (
      <div>
        <p>This is always</p>
        <Router>
            <Route path="/" exact component={Index} />
            <Route path="/blogs" component={Index} />
        </Router>
    </div>
  );
}

export default Wrapper;