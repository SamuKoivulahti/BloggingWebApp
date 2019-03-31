import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import 'primereact/resources/themes/nova-colored/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import ToTable from './ToTable';
import Wrapper from './Wrapper';

class App extends Component {
    render() {
        return (
            <Wrapper />
        );
     }
}

export default App;
