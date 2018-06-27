import { Component, OnInit } from '@angular/core';
import { MyDetailsComponent } from './../my-details/my-details.component';
import { ShowHistoryComponent } from './../show-history/show-history.component';
import { SharedService } from '../services.service';
import { SharedService1 } from '../services1.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  empId:string;
  shared;
  shared1;
  showElement:boolean;
  showElement1:boolean;
  title = 'Employee Dashboard: Sections';

  constructor(shared: SharedService,shared1: SharedService1) {
    this.empId=localStorage.getItem("tempid");
    this.shared = shared;
    this.shared1 = shared1;
   }

  ngOnInit() {
    this.shared.getEmittedValue()
    .subscribe(item => this.showElement=item);

    this.shared1.getEmittedValue()
    .subscribe(item => this.showElement1=item);
  }
  }
