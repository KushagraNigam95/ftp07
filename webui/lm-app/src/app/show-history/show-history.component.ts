import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveDetails } from './../leaveDetails';
import { Employee } from './../employee';
import { EmployeeService } from './../employee.service';
import { SharedService } from './../services.service';
@Component({
  selector: 'app-show-history',
  templateUrl: './show-history.component.html',
  styleUrls: ['./show-history.component.css'],
  providers: [ EmployeeService ]
})
export class ShowHistoryComponent implements OnInit {
  title = 'My Leave Applications Section';
  changeVar:boolean;
 
  constructor(private router: Router, private employeeService: EmployeeService, private shared: SharedService) {
    this.shared=shared;
   }

   leaveDetails:LeaveDetails[];
   listLeaveDetails(): void {
     this.employeeService.listLeaveDetails().then(leaveDetails => {
       console.log('getLeaveDetails promise resolved'+ leaveDetails.length);
       this.leaveDetails = leaveDetails;
   }
  );
}
   
  ngOnInit() {
  
  this.listLeaveDetails();
  }
  newApp() {
    this.shared.change(this.changeVar);
  //  this.router.navigate(['/apply']);
  }

}
