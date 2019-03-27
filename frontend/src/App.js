import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import 'primereact/resources/themes/nova-colored/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import ToTable from './ToTable';

class App extends Component {

    state = {};

    componentDidMount() {
        setInterval(this.hello, 250);
    }

    hello = () => {
        fetch('/blogs')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };



    render() {
        return (
            <div>
                <header>
                    <h1>BloggingWebApp</h1>
                </header>
                <form action="blogs" method="post">
                    <input type="text" placeholder="user" name="user"/>
                    <input type="text" placeholder="Title" name="title"/>
                    <input type="text" placeholder="Blog Content" name="content"/>
                    <input type="submit" value="POST"/>
                </form>
                <div><ToTable/></div>
            </div>
        );
    }
}

export default App;
