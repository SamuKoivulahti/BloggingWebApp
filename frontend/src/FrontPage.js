import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';

class FrontPage extends Component {

    constructor(properties) {
        super(properties);
        this.addListener = this.addListener.bind(this);
    }

    addListener(event) {
        return <Redirect to="/" />;
    }

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

                        <button type="submit" onClick={this.addListener} >POST</button>
                    </form>
                <div><ToTable/></div>
            </div>
        );
    }
}

export default FrontPage