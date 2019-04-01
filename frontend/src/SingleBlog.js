import React, {Component} from "react";

class SingleBlog extends Component {
    constructor(props) {
        super(props);
        this.state = {post: []};
    }

    componentDidMount() {
        let url = window.location.origin;

        fetch(`${url}/blogs/${this.props.match.params.blogId}`).then(response => response.json())
          .then(data => {
            this.setState({post: data})
          console.log(data)});
    }

    render() {
        return (
            <div>
                <h1>{this.state.post.title}</h1>
                <p>{this.state.post.content}</p>
                <p><br/><br/>Author<br/>{this.state.post.name}</p>
            </div>
        );
    }
}

export default SingleBlog;