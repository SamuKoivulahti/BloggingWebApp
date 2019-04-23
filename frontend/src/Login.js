import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button} from 'primereact/button';
import {InputText} from 'primereact/inputtext';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {user: '', fetchedUser: [], showRegistration: false};
    this.usernameCheck = this.usernameCheck.bind(this);
    this.showRegister = this.showRegister.bind(this);
    this.confirmPassword = this.confirmPassword.bind(this);
    this.check = this.check.bind(this);
  }

  showRegister() {
    this.setState({showRegistration: !this.state.showRegistration});
  }

  confirmPassword() {

  }

  addAdmin() {

  }

  registerAccount() {
    let url = window.location.origin + "/users";

    let data = new FormData();
    data.append("name", this.state.user);
    data.append("admin", false);
    fetch(url, {
                  method:"POST",
                  mode: "cors",
                  credentials:"omit",
                  body: data
                }).then(response => console.log(response))
                  .catch(error => console.log(error));
    localStorage.setItem("user", this.state.user);
    localStorage.setItem("admin", "false");
    localStorage.setItem("loggedin", "true");
    window.location.reload();
  }

  usernameCheck(loginAttempt) {
    let url = window.location.origin;

      fetch(`${url}/users/`
      ).then(response => response.json())
        .then(data => this.setState({fetchedUser: data},() => this.check(loginAttempt)));
  }

  logout() {
    localStorage.setItem("loggedin", "false");
    localStorage.setItem("user", "");
    localStorage.setItem("admin", "");
    this.setState({user: "", fetchedUser: [], showRegistration: false});
    window.location.reload();
  }

  check(loginAttempt) {
    console.log(this.state.fetchedUser);

    for (var each in this.state.fetchedUser) {
      console.log(this.state.fetchedUser[each].name);

      if (this.state.fetchedUser[each].name === this.state.user) {
        if (loginAttempt) {
          console.log("LÖYTY");
          localStorage.setItem("user", this.state.user);
          localStorage.setItem("admin", this.state.fetchedUser[each].admin);
          localStorage.setItem("loggedin", "true");
          window.location.reload();
          return;
        } else {
          alert("Username already exists, choose another!");
          return;
        }
      }
    }

    if (loginAttempt) {

    } else {
      this.registerAccount();
    }
  }

  render() {
    return (
      <div>
          {localStorage.getItem("loggedin") === "true" ? (<Button label="Log out" onClick={() => this.logout()}/>) :
          <div>
          <div>
            <InputText placeholder="Username" value={this.state.user} onChange={e => this.setState({user: e.target.value})}/>
            {this.state.showRegistration ? (<InputText placeholder="Admin Password"/>) : ""}
          </div>
          <div>
            <InputText placeholder="Password"/>
            {this.state.showRegistration ? (<InputText placeholder="Confirm password"/>) : ""}
          </div>
          {!this.state.showRegistration ? (<Button label="Login" onClick={() => this.usernameCheck(true)}/>) : (<Button label="Back" onClick={this.showRegister}/>)}
          {!this.state.showRegistration ? (<Button label="Register" onClick={this.showRegister}/>) : (<Button label="Register" onClick={() => this.usernameCheck(false)}/>)}
          </div>
          }
      </div>
    );
  }
}

export default Login;