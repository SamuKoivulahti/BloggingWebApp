import React, {Component} from "react";
import {Button} from 'primereact/button';
import {InputTextarea} from 'primereact/inputtextarea';
import {InputText} from 'primereact/inputtext';


class SingleBlog extends Component {
    constructor(props) {
        super(props);
        this.state = {show: false};
        this.swicharoo = this.swicharoo.bind(this);
        this.textNormal = this.textNormal.bind(this);
        this.textEdit = this.textEdit.bind(this);
        this.clicked = this.clicked.bind(this);
    }

    componentDidMount() {
        let url = window.location.origin;

        fetch(`${url}/blogs/${this.props.match.params.blogId}`).then(response => response.json())
          .then(data => {
            this.setState({...data})
          console.log(this.state)});
    }


    textNormal() {
        return (
          <div>
              <h1>{this.state.title}</h1>
              <p>{this.state.content}</p>
              <p><br/><br/>Author<br/>{this.state.name}</p>
              <Button label="Edit" onClick={this.clicked}/>
          </div>
        );
    }

    editContent() {
        let url = window.location.origin;
        let data = new FormData();
        data.append("title", this.state.title)
        data.append("content", this.state.content)
        fetch(`${url}/blogs/edit/${this.state.id}`, {
              method:"POST",
              mode: "cors",
              credentials:"omit",
              body: data
            }).then(response => console.log(response))
              .catch(error => console.log(error));
        this.setState({show: false})
        console.log(this.state)
        window.location.reload();
    }

    textEdit() {
        return (
            <div>
                <div><InputText defaultValue={this.state.title} onChange={e => this.setState({title: e.target.value})}/></div>
                <div><InputTextarea rows={5} cols={30} defaultValue={this.state.content} onChange={e => this.setState({content: e.target.value})} autoResize={true}/></div>
                <p><br/><br/>Author<br/>{this.state.name}</p>
                <Button label="Save" onClick={() => this.editContent()}/>
            </div>
        )
    }

    swicharoo() {
        if (this.state.show) {
          return this.textEdit()
        } else {
          return this.textNormal()
        }
    }

    clicked() {
        this.setState({show: true});
    }

    render() {
        return (<div>{this.swicharoo()}</div>);
    }
}

export default SingleBlog;