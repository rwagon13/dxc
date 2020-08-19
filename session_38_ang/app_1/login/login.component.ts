import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName : string;
  passWord : string;

  validate() {
    if (this.userName=="Sanil" && this.passWord=="Sanil") {
      this._router.navigate(["/menu"]);
    } else {
      alert("Invalid Credentials...");
    }
  }
  constructor(private _router : Router) { }

  ngOnInit() {
  }

}
