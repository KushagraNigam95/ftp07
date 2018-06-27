import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
@Component({
  selector: 'app-my-details',
  providers:[EmployeeService],
  templateUrl: './my-details.component.html',
  styleUrls: ['./my-details.component.css']
 
})
export class MyDetailsComponent implements OnInit {
  employee: Employee;
  title = 'My Details Section';
  constructor(private employeeService: EmployeeService) { 
    this.employee=new Employee();
    
  }
 
  ngOnInit() {
   this.getEmployee();
  }

  
  getEmployee(): void {
    this.employeeService.getEmployee().then(employee => {
      console.log('getEmployee promise resolved : ' + employee);
      this.employee = employee;
    }
  );
}

}