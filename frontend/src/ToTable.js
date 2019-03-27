import React, {Component} from "react";

import {TreeTable} from 'primereact/treetable';
import {Column} from 'primereact/column';

class ToTable extends Component {
  constructor(props) {
    super(props);

    this.state = {posts: []};
  }

  componentDidMount() {
    let url = window.location.origin;

    fetch(`${url}/blogs`).then(response => response.json())
      .then(data => this.setState({posts: data}));
  }

  render() {
    return (
      <TreeTable responsive={true} value={this.state.posts.map(item => {
        return {key: item.id, data: {...item}};
      })}>
        <Column field="name" header="Name" />
        <Column field="title" header="Title" />
        <Column field="content" header="Content" />
      </TreeTable>
    );
  }
}

export default ToTable;