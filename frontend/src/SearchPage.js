import React, {Component} from "react";
import {Link} from "react-router-dom";
import ToTable from './ToTable';
import { Redirect } from 'react-router';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import {Accordion, AccordionTab} from 'primereact/accordion'

class SearchPage extends Component {

    constructor(properties) {
        super(properties);
        this.getTable = this.getTable.bind(this);
        this.searchedList = this.searchedList.bind(this);
        this.state = {fetchedPosts: [], posts: ''};
    }

    getTable() {
        let url = window.location.origin;
        fetch(`${url}/blogs/search/${this.state.posts}`)
        .then(response => response.json())
        .then(data => {
        this.setState({fetchedPosts: data})
        console.log(data)});
    }

    searchedList() {
        return (<Accordion multiple={true}>{this.state.fetchedPosts.map(post => this.createTab(post))}</Accordion>);
    }

    createTab(post) {
        return (<AccordionTab key={post.id} header={post.title}>{post.content}<Link to={`/blogs/${post.id}`}><Button label="View"/></Link></AccordionTab>);
    }

    render() {
        const {posts} = this.state;
        return (
            <div>
                <div>
                    <InputText placeholder="search" value={this.state.posts} onChange={e => this.setState({posts: e.target.value})}/>
                    <Button label="Search" onClick={() => this.getTable()}/>
                </div>
                {this.searchedList()}
            </div>
        );
    }
}

export default SearchPage