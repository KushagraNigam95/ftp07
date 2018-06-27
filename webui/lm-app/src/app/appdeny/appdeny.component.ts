import { Component, OnInit } from '@angular/core';
import { Employee } from "./../employee";
import { Router } from '@angular/router';
import { LeaveDetails } from "./../leavedetails";
import { EmployeeService } from "./../employee.service";
import { SharedService } from '../services.service';
import { SharedService1 } from '../services1.service';
@Component({
  selector: 'app-appdeny',
  templateUrl: './appdeny.component.html',
  styleUrls: ['./appdeny.component.css'],
  providers: [ EmployeeService ]
})

export class AppdenyComponent  {
  message:String;
  empId;
  leaveDetail;
  shared1;
  employee : Employee;
  emplID : number;
  showElement1:boolean;
  changeVar1:boolean;
  leaveDetails : LeaveDetails;
  levId;
  title = 'Approve Deny Leave';
  constructor(private router:Router,private employeeService : EmployeeService,shared:SharedService,shared1:SharedService1 ) {
    this.message='';
    this.leaveDetails = new LeaveDetails();
    this.employee = new Employee();
    this.leaveDetails.empId=this.leaveDetails.empId;
    this.empId = localStorage.getItem('emp');
    this.shared1=shared1;
    this.leaveDetail = localStorage.getItem('leaDetail');
    console.log(JSON.stringify(this.employee));
    this.employee=JSON.parse(this.empId);
    this.leaveDetails=JSON.parse(this.leaveDetail);
  }

  doApprove(){
    this.employeeService.doApprove(this.levId,this.leaveDetails).subscribe(
      data => {
       // this.message="Appproved Sucessfully";
       // alert('Leave is approved');
       this.showElement1=true;
       this.shared1.change1(this.showElement1);
        this.router.navigate(['/dashboard']);
      },
      err => {
        this.message="Not Appproved";
        console.log(err);
        this.router.navigate(['/dashboard']);
      });

  }
  doDeny(){
    this.employeeService.doDeny(this.leaveDetails).subscribe(
      data => {
        this.message="Denied";
        this.showElement1=true;
        this.shared1.change1(this.showElement1);
       // alert('Leave is Denied');
        this.router.navigate(['/dashboard']);
      },
      err => {
        this.message="Not Denied";
        console.log(err);
      });

  }
  doCancel(){
    this.showElement1=true;
    this.shared1.change1(this.showElement1);
   
      this.router.navigate(['/dashboard']);

  }

  getEmployee(): void {
    this.employeeService.getEmployee().then(employee => {
      console.log('getEmployee promise resolved : ' + employee);
      this.employee = employee;
    }
  );
  }
  LeaveDetails(): void {
      
      this.employeeService.LeaveDetails(this.leaveDetails.levId, this.leaveDetails).subscribe(leavedetails => {
        console.log('getEmployees promise resolved ');
        this.getEmployee();
        this.leaveDetails = leavedetails;
  }
  );
  }
  ngOnInit() {   
    this.LeaveDetails();
    

  }
}
