import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Router } from '@angular/router';
import { Employee } from './../employee';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ EmployeeService ]
})
export class HomeComponent implements OnInit {
  title = '';
  employees: Employee[];


  constructor(private employeeService: EmployeeService, private router:Router) { }

  
  getEmployees(): void {
      this.employeeService.getEmployees().then(employees => {
        console.log('getEmployees promise resolved : ' + employees.length);
        this.employees = employees;
      
      }
    );
  }

  login(id,mid){
    localStorage.setItem("tempid",id);
    localStorage.setItem("tempMgrId",mid);
    this.router.navigate(['/login']);
  }

  ngOnInit() {
    this.getEmployees();
  }

}
