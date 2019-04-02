import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button} from 'primereact/button';
import {InputText} from 'primereact/inputtext';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {user: '', fetchedUser: []};
    this.usernameCheck = this.usernameCheck.bind(this);
  }

  usernameCheck() {
    let url = window.location.origin;

      fetch(`${url}/users/${this.state.user}`).then(response => response.json())
        .then(data => {
          this.setState({fetchedUser: data})
        console.log(data)});


  }

  render() {
    return (
      <div>
          <div>
            <InputText placeholder="Username" value={this.state.user} onChange={e => this.setState({user: e.target.value})}/>
          </div>
          <div>
            <InputText placeholder="Password"/>
          </div>
          <Button label="Login" onClick={this.usernameCheck}/>
      </div>
    );
  }
}

export default Login;