import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import TabMenuBar from './TabMenuBar';

class AddPost extends Component {

    constructor(properties) {
        super(properties);
        this.state = {title: '', content: ''};
        this.post = this.post.bind(this);
    }

    post() {
        let url = window.location.origin + "/blogs";

        let data = new FormData();
        data.append("user", localStorage.getItem("user"))
        data.append("title", this.state.title)
        data.append("content", this.state.content)
        fetch(url, {
              method:"POST",
              mode: "cors",
              credentials:"omit",
              body: data
            }).then(response => console.log(response))
              .catch(error => console.log(error));
        window.location.reload();
    }

    render() {
        const {user} = this.state;
        return (
            <div>
                <InputText placeholder="Title" value={this.state.title} onChange={e => this.setState({title: e.target.value})}/>
                <InputText placeholder="Content" value={this.state.content} onChange={e => this.setState({content: e.target.value})}/>
                <Button label="Post" onClick={() => this.post()}/>
            </div>
        );
    }
}

export default AddPost