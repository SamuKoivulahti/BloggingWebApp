import React, {Component} from "react";
import {Button} from 'primereact/button';
import {InputTextarea} from 'primereact/inputtextarea';
import {InputText} from 'primereact/inputtext';
import {Checkbox} from 'primereact/checkbox';
import {Panel} from 'primereact/panel';


class SingleBlog extends Component {
    constructor(props) {
        super(props);
        this.state = {show: false, comment: '', comments: []};
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



    createCommentsList() {
        return (<div>{this.state.comments.map((com, index) => this.createComment(com, index))}</div>);
    }

    createComment(com, id) {
        return (<Panel style={{marginBottom: '1em'}, {marginTop: '1em'}}>
                    {com}
                    {localStorage.getItem("admin") === "true" ?
                      (<Button style={{float: 'right'}, {margin: '1em'}} className="p-button-danger p-button-rounded" icon="pi pi-times" onClick={e => this.deleteComment(id)}/>) : ("")}
                </Panel>);
    }

    textNormal() {
        return (
          <div>
              <h1>{this.state.title}</h1>
              <p>{this.state.content}</p>
              <p><br/><br/>Author<br/>{this.state.name}</p>
              <Button label="Edit" onClick={this.clicked}/>
              <br/>
              <br/>
              <Checkbox onChange={e => this.setState({checked: e.checked})} checked={this.state.checked}></Checkbox>
              <label>Like</label>
              <br/>
              <div><InputTextarea rows={5} cols={30} defaultValue={this.state.comment} onChange={e => this.setState({comment: e.target.value})} autoResize={true}/></div>
              <Button label="Comment" onClick={() => this.addComment()}/>
              <br/>
              <br/>
              {this.createCommentsList()}
          </div>
        );
    }

    deletePost() {
      let url = window.location.origin;
      fetch(`${url}/blogs/${this.state.id}`, {
                    method:"DELETE",
                    mode: "cors",
                    credentials:"omit",
                    body: ""
                  }).then(response => console.log(response))
                    .catch(error => console.log(error));
              this.setState({show: false})
              console.log(this.state)
              window.location.assign("/");
    }

    deleteComment(id) {
      let url = window.location.origin;

      let data = new FormData();
      data.append("commentId", id);
      fetch(`${url}/blogs/deleteComment/${this.state.id}`, {
             method:"DELETE",
             mode: "cors",
             credentials:"omit",
             body: data
           }).then(response => console.log(response))
             .catch(error => console.log(error));
        this.setState({show: false})
        console.log(this.state)
        window.location.reload();
    }

    addComment() {
        let url = window.location.origin;
        let data = new FormData();
        data.append("content", this.state.comment)
        fetch(`${url}/blogs/addComment/${this.state.id}`, {
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
        if (this.state.show) {
            this.setState({show: false});
        } else {
            this.setState({show: true});
        }
    }

    render() {
        return (<div>
            <div>
              <Button label="Back" onClick={() => window.location.assign("/")}/>
              {localStorage.getItem("admin") === "true" ? (<Button className="p-button-danger" label="DELETE" onClick={() => this.deletePost()}/>) : ("")}
            </div>
            <div>
              {this.swicharoo()}
            </div>
          </div>);
    }
}

export default SingleBlog;