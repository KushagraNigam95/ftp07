import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { LoginComponent} from './login/login.component';
import {FormsModule} from '@angular/forms';
import {rootRouterConfig} from './app.routes';
import {RouterModule} from '@angular/router';
import { MyDetailsComponent } from './my-details/my-details.component';
import { EmployeeService } from './employee.service';
import { AppComponent } from './app.component';
import { ApplyleaveComponent} from './applyleave/applyleave.component';
import {ManagerDetailsComponent} from './managerdetails/managerdetails.component';
import { PendingComponent } from './pending/pending.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ShowHistoryComponent } from './show-history/show-history.component';
import { HomeComponent } from './home/home.component';
import { AppdenyComponent} from './appdeny/appdeny.component';
import { SharedService } from './services.service';
import { SharedService1 } from './services1.service';



@NgModule({
  declarations: [
    AppComponent,
    MyDetailsComponent,
    ApplyleaveComponent,
    LoginComponent,
    ManagerDetailsComponent,
    PendingComponent,
    DashboardComponent,
    ShowHistoryComponent,
    HomeComponent,
    AppdenyComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(rootRouterConfig)
  ],
  providers: [ EmployeeService, SharedService, SharedService1],
  bootstrap: [AppComponent]
})
export class AppModule { }
