import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import TabMenuBar from './TabMenuBar';
import AddPost from './AddPost'

class FrontPage extends Component {

    constructor(properties) {
        super(properties);
    }

    tabMenus() {
        return [
            {index: 0, label: 'Blog Posts', component: <ToTable/>},
            {index: 1, label: '+Add Post', component: <AddPost/>}
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