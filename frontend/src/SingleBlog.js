import React, {Component} from "react";
import {Button} from 'primereact/button';
import {InputTextarea} from 'primereact/inputtextarea';
import {InputText} from 'primereact/inputtext';
import {Checkbox} from 'primereact/checkbox';
import {Panel} from 'primereact/panel';
import {Card} from 'primereact/card';


class SingleBlog extends Component {
    constructor(props) {
        super(props);
        this.state = {show: false, comment: '', comments: [], like: 'Like'};
        this.swicharoo = this.swicharoo.bind(this);
        this.textNormal = this.textNormal.bind(this);
        this.textEdit = this.textEdit.bind(this);
        this.clicked = this.clicked.bind(this);
    }

    componentDidMount() {
        let url = window.location.origin;

        fetch(`${url}/blogs/${this.props.match.params.blogId}`).then(response => response.json())
          .then(data => {
          this.setState({...data}, () => this.getUserLikes(url))
          console.log(this.state)});

    }

    getUserLikes(url) {
        console.log(localStorage.getItem("user"))
        if (localStorage.getItem("loggedin") === "true") {
            fetch(`${url}/users/${localStorage.getItem("user")}`).then(response => response.json())
              .then(data => {
              this.setState({...data}, () => this.isLiked())
              console.log(this.state)});
        }
    }

    isLiked() {
        for (var index = 0; index < this.state.likes.length; index++) {
            console.log(this.state.likes.length)
            console.log('id: ' + this.state.id + ' index: ' + this.state.likes[index])
            if (this.state.likes[index] === this.state.id) {
                this.setState({like: 'Un-like'})
                break;
            }
        }

    }

    createCommentsList() {
        return (<div>{this.state.comments.map((com, index) => this.createComment(com, index))}</div>);
    }

    createComment(com, id) {
        return (<Card key={id} style={{marginBottom: '1em'}, {marginTop: '1em'}}>
                    {com}
                    {localStorage.getItem("admin") === "true" ?
                      (<Button style={{float: 'right'}, {margin: '1em'}} className="p-button-danger p-button-rounded" icon="pi pi-times" onClick={e => this.deleteComment(id)}/>) : ("")}
                </Card>);
    }

    createTitle() {
        let text = this.state.title + " | " + this.state.name;
        return text;
    }


    textNormal() {
        return (
          <div>
              <Panel header={this.createTitle()}>{this.state.content}</Panel>
              {localStorage.getItem("admin") === "true" ? (<Button label="Edit" onClick={this.clicked}/>) : ("")}

              <br/>
              <br/>
               {localStorage.getItem("loggedin") === "true" ? (
               <div>
                 <Button label={this.state.like} onClick={() => this.likePost()}/>
                 <br/>
                 <div>
                   <InputTextarea rows={5} cols={30} defaultValue={this.state.comment} onChange={e => this.setState({comment: e.target.value})} autoResize={true}/>
                 </div>
                   <Button label="Comment" onClick={() => this.addComment()}/>
               </div>) : ("")}
              <br/>
              <br/>
              {this.createCommentsList()}
          </div>
        );
    }

    likePost() {
      let data = new FormData();
      data.append("user", localStorage.getItem("user"));
      let url = window.location.origin;

      if (this.state.like === 'Like') {
        fetch(`${url}/blogs/like/${this.state.id}`, {
                method:"POST",
                mode: "cors",
                credentials: "omit",
                body: data
                }).then(response => console.log(response))
                .catch(error => console.log(error));
        this.setState({like: 'Un-like'})
      } else {
      fetch(`${url}/blogs/like/del/${this.state.id}`, {
                      method:"DELETE",
                      mode: "cors",
                      credentials: "omit",
                      body: data
                      }).then(response => console.log(response))
                      .catch(error => console.log(error));
        this.setState({like: 'Like'})
      }
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