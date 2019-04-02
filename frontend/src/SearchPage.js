import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';

class SearchPage extends Component {

    constructor(properties) {
        super(properties);
        this.getTable = this.getTable.bind(this);
        this.searchedList = this.searchedList.bind(this);
        this.state = {searched: ''};
    }

    getTable() {
        let data = new FormData();
        fetch('http://localhost:8080/blogs/search/${this.state.searched}')
        .then(response => response.json())
        .then(data => this.state.searchedList)
    }

    searchedList() {

    }

    render() {
    const {searched} = this.state;
        return (
            <div>
                <InputText placeholder="Search" value={this.state.searched} onChange={e => this.setState({searched: e.target.value})}/>
                <Button label="Search" onClick={() => this.getTable("http://localhost:8080/blogs/search/${this.state.searched}")}/>
            </div>
        );
    }
}

export default SearchPage