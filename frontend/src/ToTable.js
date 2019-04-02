import React, {Component} from "react";

import {TreeTable} from 'primereact/treetable';
import {Column} from 'primereact/column';
import {Button} from 'primereact/button';
import {Accordion,AccordionTab} from 'primereact/accordion';
import {Link} from "react-router-dom";

class ToTable extends Component {
  constructor(props) {
    super(props);

    this.createTab = this.createTab.bind(this);
    this.createTitle = this.createTitle.bind(this);
    this.createPostList = this.createPostList.bind(this);
    this.actionButton = this.actionButton.bind(this);
    this.state = {posts: []};
  }

  componentDidMount() {
    let url = window.location.origin;

    fetch(`${url}/blogs`).then(response => response.json())
      .then(data => {
        this.setState({posts: data})
    console.log(data)});
  }

  createPostList() {
    return (<Accordion multiple={true}>{this.state.posts.map(post => this.createTab(post))}</Accordion>);
  }

  createTitle(post) {
    let text = post.title + " | " + post.name;
    return text
  }

  createTab(post) {
    return (<AccordionTab key={post.id} header={this.createTitle(post)}>
            {post.content}
            <Link to={`/blogs/${post.id}`}>
                <Button style={{float: 'right'}} label="View"/>
            </Link>
            <p><br/>{post.name}</p>
        </AccordionTab>);
  }

  actionButton() {

    return (<Button onClick={event => console.log(event)} >View</Button>);
  }

/*
  render() {
    return (
      <TreeTable responsive={true} value={this.state.posts.map(item => {
        return {key: item.id, data: {...item}};
      })}>
        <Column field="name" header="Name" />
        <Column field="title" header="Title" />
        <Column field="content" header="Content" />
        <Column body={this.actionButton} header="Actions" style={{ textAlign: 'center', width: '6em' }} />
      </TreeTable>
    );
  }*/

  render() {
    return (
      <div>
        {this.createPostList()}
      </div>
    );
  }
}

export default ToTable;