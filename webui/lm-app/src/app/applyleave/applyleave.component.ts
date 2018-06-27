import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
//import { Apply} from '../services/app.applyleave.service'
import { EmployeeService } from '../employee.service';
import {LeaveDetails } from '../leavedetails';
import { SharedService } from '../services.service';

@Component({
  selector: 'app-apply-leave',
  providers:[EmployeeService],
  templateUrl: './applyleave.component.html',
  styleUrls: ['./applyleave.component.css']
})
export class ApplyleaveComponent implements OnInit {
  
  message:String;
  leaveDetails:LeaveDetails;
  empId;
  shared;
  title = 'Apply Leave';
  showElement:boolean;
  changeVar:boolean;
  constructor(private router:Router, public empservice:EmployeeService,private route:ActivatedRoute,shared:SharedService) { 
    this.message='';
    this.leaveDetails=new LeaveDetails();
    this.empId=localStorage.getItem("tempid");
    this.shared=shared;
  }

  doApply(){
    this.empservice.doApply(this.empId,this.leaveDetails).subscribe(
      data => {
        this.message=data;
        alert(data);
        this.router.navigate(['/dashboard']);

      },
      err => {
        console.log(err);
      });

  }
  cancel() {
    this.showElement=true;
    this.shared.change(this.showElement);
    this.router.navigate(['/dashboard']);
  }

  ngOnInit() {
  }

}
