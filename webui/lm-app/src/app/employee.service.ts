import { Employee } from './employee';
import { LeaveDetails } from './leaveDetails';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

import { Http } from '@angular/http';
import { Injectable } from '@angular/core';


@Injectable()
export class EmployeeService {
    tempid:number;
    managerid:number;
    constructor(private http: Http) {
        this.tempid=parseInt(localStorage.getItem("tempid"));
        this.managerid=parseInt(localStorage.getItem("tempMgrId"));
    }

    getEmployees(): Promise<Employee[]> {
        console.log('getEmployees called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees')
        .toPromise()
        .then(response => response.json() as Employee[])
        .catch(this.handleError);
    }
    
    getEmployee(): Promise<Employee> {
        console.log('getEmployees called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees/'+ this.tempid)
        .toPromise()
        .then(response => response.json() as Employee)
        .catch(this.handleError);
    }

    manager(): Promise<Employee> {
        console.log('manager called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees/manager/'+ this.tempid)
        .toPromise()
        .then(response => response.json() as Employee)
        .catch(this.handleError);
    }

    listLeaveDetails(): Promise<LeaveDetails[]> {
        console.log('getLeaveHistory called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees/LeaveHistory/'+ this.tempid)
        .toPromise()
        .then(response => response.json() as LeaveDetails[])
        .catch(this.handleError);

    }

    LeaveDetails(levId :number, leavedetails): Observable<LeaveDetails> {
        console.log('getEmployees called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees/leavedetails/'+levId+'/'+this.tempid)
        .map(response => response.json()  as LeaveDetails)
    }
    
    doLogin(employee:Employee):Observable<String>{
        return this.http.get('http://localhost:8080/ftp07/api/employees/'+employee.empID)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }

    doApply(empId:number,leavedetails:LeaveDetails):Observable<String>{
       return this.http.post('http://localhost:8080/ftp07/api/employees/applyleave/'+empId,leavedetails)
        .map(response=>response.text())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    
      }

    doApprove(levId :number,leavedetails:LeaveDetails):Observable<String>{
        return this.http.post('http://localhost:8080/ftp07/api/employees/leavedetails/'+leavedetails.levId,leavedetails)
         .map(response=>response.text())
         .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
     
       }
    
    doDeny(leavedetails:LeaveDetails):Observable<String>{
        return this.http.post('http://localhost:8080/ftp07/api/employees/denyleave/'+leavedetails.levId,leavedetails)
         .map(response=>response.text())
         .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
     
       }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    empPendingDetails(): Promise<Employee[]> {
        console.log('empPendingDetails called on employee.service');
        return this.http.get('http://localhost:8080/ftp07/api/employees/listempbyid/'+ this.tempid)  
        .toPromise()
        .then(response => response.json() as Employee[])
        .catch(this.handleError);
    }

    empLeaveDetails(): Promise<LeaveDetails[]> {
      console.log('empLeaveDetails called on employee.service');
      return this.http.get('http://localhost:8080/ftp07/api/employees/listemppending/'+ this.tempid )  
      .toPromise()
      .then(response => response.json() as LeaveDetails[])
      .catch(this.handleError);
  }
}
