import React, {Component} from "react";
import {Link} from "react-router-dom";
import {Button} from 'primereact/button';
import {InputText} from 'primereact/inputtext';

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {user: '', fetchedUser: [], showRegistration: false, adminPass: '', pass: '', passConfirm: ''};
    this.usernameCheck = this.usernameCheck.bind(this);
    this.showRegister = this.showRegister.bind(this);
    this.confirmPassword = this.confirmPassword.bind(this);
    this.check = this.check.bind(this);
  }

  showRegister() {
    this.setState({showRegistration: !this.state.showRegistration, adminPass: '', passConfirm: ''});
  }

  confirmPassword() {
    let MIN_PASSWORD_LENGTH = 8;
    let MAX_PASSWORD_LENGTH = 160;

    let success = {
      admin: false,
      success: false
    }

    if (this.state.user.length < 3) {
      alert("Username too short!");
      return success;
    }

    if (this.state.pass !== this.state.passConfirm) {
      alert("Passwords do not match!");
      return success;
    }

    if (this.state.pass.length < MIN_PASSWORD_LENGTH || this.state.pass.length > MAX_PASSWORD_LENGTH) {
      alert("Incorrect password length!");
      return success;
    }

    if (this.state.adminPass === "admin") {
      success.admin = true;
    } else if (this.state.adminPass === "") {

    } else {
      alert("Wrong admin pass! Empty the field if you are not admin!");
      return success;
    }

    success.success = true;

    return success;
  }

  registerAccount() {
    let url = window.location.origin + "/users";

    let success = this.confirmPassword();

    if (!success.success) {
      return;
    }

    console.log(this.state)
    let data = new FormData();
    data.append("name", this.state.user);
    data.append("admin", success.admin);
    data.append("pass", this.state.pass);
    fetch(url, {
                  method:"POST",
                  mode: "cors",
                  credentials:"omit",
                  body: data
                }).then(response => console.log(response))
                  .catch(error => console.log(error));
    localStorage.setItem("user", this.state.user);
    localStorage.setItem("admin", success.admin);
    localStorage.setItem("loggedin", "true");
    this.setState({user: '', fetchedUser: [], showRegistration: false, adminPass: '', pass: '', passConfirm: ''});
  }

  usernameCheck(loginAttempt) {
    let url = window.location.origin;

      fetch(`${url}/users/`
      ).then(response => response.json())
        .then(data => this.setState({fetchedUser: data},() => this.check(loginAttempt)));
  }

  reload() {
    window.location.reload();
  }

  logout() {
    localStorage.setItem("loggedin", "false");
    localStorage.setItem("user", "");
    localStorage.setItem("admin", "");
    this.setState({user: '', fetchedUser: [], showRegistration: false, adminPass: '', pass: '', passConfirm: ''}, () => this.reload());
  }

  check(loginAttempt) {
    console.log(this.state.fetchedUser);

    for (var each in this.state.fetchedUser) {
      console.log(this.state.fetchedUser[each].name);

      if (this.state.fetchedUser[each].name === this.state.user) {
        if (loginAttempt) {
          console.log("LÖYTY");
          if (this.state.pass !== this.state.fetchedUser[each].pass) {
            alert("Wrong password!");
            return;
          }

          localStorage.setItem("user", this.state.user);
          localStorage.setItem("admin", this.state.fetchedUser[each].admin);
          localStorage.setItem("loggedin", "true");
          this.setState({user: '', fetchedUser: [], showRegistration: false, adminPass: '', pass: '', passConfirm: ''}, () => this.reload());
          return;
        } else {
          alert("Username already exists, choose another!");
          return;
        }
      }
    }

    if (loginAttempt) {
      if (localStorage.getItem("loggedin") !== "true") {
        alert("No user named " + this.state.user);
      }
    } else {
      this.registerAccount();
    }
  }

  render() {
    return (
      <div>
        {localStorage.getItem("loggedin") === "true" ? (<div className="buttonDiv"><Button label="Log out" onClick={() => this.logout()}/></div>) :
          <div>
            <div>
              <InputText placeholder="Username" value={this.state.user} onChange={e => this.setState({user: e.target.value})}/>
              {this.state.showRegistration ? (<InputText placeholder="Admin Password" value={this.state.adminPass} onChange={e => this.setState({adminPass: e.target.value})}/>) : ""}
            </div>
            <div>
              <InputText placeholder="Password" value={this.state.pass} onChange={e => this.setState({pass: e.target.value})}/>
              {this.state.showRegistration ? (<InputText placeholder="Confirm password" value={this.state.passConfirm} onChange={e => this.setState({passConfirm: e.target.value})}/>) : ""}
            </div>
            {!this.state.showRegistration ? (<div className="buttonDiv"><Button label="Login" onClick={() => this.usernameCheck(true)}/></div>) :
             (<div className="buttonDiv"><Button label="Back" onClick={this.showRegister}/></div>)}
            {!this.state.showRegistration ? (<div className="buttonDiv"><Button label="Register" onClick={this.showRegister}/></div>) :
             (<div className="buttonDiv"><Button label="Register" onClick={() => this.usernameCheck(false)}/></div>)}
          </div>
        }
      </div>
    );
  }
}

export default Login;