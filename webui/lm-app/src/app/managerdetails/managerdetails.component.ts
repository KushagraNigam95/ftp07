import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';

@Component({
  selector: 'app-managerdetails',
  templateUrl: './managerdetails.component.html',
  styleUrls: ['./managerdetails.component.css']
})
export class ManagerDetailsComponent implements OnInit {
  employee: Employee;
  empMgrId;
  constructor(private employeeService: EmployeeService) { 
    this.employee=new Employee();
    this.empMgrId=localStorage.getItem("tempMgrId");
  }
 
  ngOnInit() {
   this.manager();
  }

  
  manager(): void {
    this.employeeService.manager().then(mydetails => {
      console.log('Manager promise resolved : ' + mydetails);
      this.employee = mydetails;
    }
  );
}

}