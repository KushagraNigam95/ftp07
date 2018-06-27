
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { LeaveDetails } from './../leavedetails';
import { Router } from '@angular/router';
import { SharedService1 } from './../services1.service';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
  styleUrls: ['./pending.component.css']
  
})
export class PendingComponent implements OnInit {
  employees: Employee[];
  leavedetails: LeaveDetails[]; 
  selectedRow;
  leave;
  emp;
  levDetail;
  showButton:boolean = false;
  select:boolean = false;
  changeVar1:boolean;
  shared1;
  showElement:boolean;
  title = ' My Reporting Employees Pending Leave Applications Sections';
  constructor(private employeeService: EmployeeService, private router:Router, shared1: SharedService1) { 
    this.shared1=shared1;
  }
 
  ngOnInit() {
   this.pendingDetails();

   
  }


  pendingDetails(): void {
    this.employeeService.empPendingDetails().then(employees => {
      console.log('getPendingDetails promise resolved : ' + employees);
      this.leaveDetails();
      this.employees = employees;
    }
  );
}
leaveDetails(): void {
  this.employeeService.empLeaveDetails().then(leavedetails => {
    console.log('getLeaveDetails promise resolved : ' + leavedetails);
    this.leavedetails = leavedetails;
  }
);
}

clickRow(leavedetails, employee) {
  this.selectedRow=leavedetails.levId;
  console.log(leavedetails);
  this.showButton=true;
  this.leave=leavedetails;
  this.emp=employee;
}
deselect(){
  this.showButton=false;
}

doApproveDeny(){
  this.showElement=true;
  this.shared1.change1(this.changeVar1);
  console.log(this.leave);
  localStorage.setItem('emp', JSON.stringify(this.emp));
  localStorage.setItem('leaDetail', JSON.stringify(this.leave));
  //this.router.navigate(['/appdeny']);

}

}