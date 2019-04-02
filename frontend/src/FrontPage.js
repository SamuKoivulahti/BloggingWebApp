import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import TabMenuBar from './TabMenuBar';
import AddPost from './AddPost';
import SearchPage from './SearchPage';
import Login from './Login';

class FrontPage extends Component {

    constructor(properties) {
        super(properties);
    }

    tabMenus() {
        return [
            {index: 0, label: 'Blog Posts', component: <ToTable/>},
            {index: 1, label: '+Add Post', component: <AddPost/>},
            {index: 2, label: 'Search', component: <SearchPage/>},
            {index: 3, label: 'Login', component: <Login/>}
        ];
    }

    render() {
        return (
            <div>
                <div><TabMenuBar items={this.tabMenus()}/></div>
            </div>
        );
    }
}

export default FrontPage