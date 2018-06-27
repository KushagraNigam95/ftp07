import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import {LeaveDetails } from '../leavedetails';
import {Employee} from '../employee';
import {Login} from '../login';

@Component({
  selector: 'app-login',
  providers: [EmployeeService],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
  
})
export class LoginComponent implements OnInit {

  model=new Login();
  usrid : string;
  title = 'Login Screen';
  
  ngOnInit() {
    
    this.usrid=localStorage.getItem("tempid");
    this.model.uid=this.usrid;
   }
   constructor(private router: Router) {
     
     
  }
  doLogin() {
          this.router.navigate(['/dashboard']);
        } 

}