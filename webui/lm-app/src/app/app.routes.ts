import {Routes} from '@angular/router';
import {MyDetailsComponent} from './my-details/my-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent} from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ApplyleaveComponent } from './applyleave/applyleave.component';
import{AppdenyComponent} from './appdeny/appdeny.component';
import { ShowHistoryComponent } from './show-history/show-history.component';
import { PendingComponent } from './pending/pending.component';



export const rootRouterConfig: Routes =[
   {path: '',redirectTo: 'home',pathMatch: 'full'},
    {path: 'home',component: HomeComponent},
    {path: 'login',component: LoginComponent},
    { path : 'show-history/:empId', component : ShowHistoryComponent},
    {path: 'dashboard',component: DashboardComponent},
    {path: 'apply',component: ApplyleaveComponent},
    {path: 'pending/ :empId',component: PendingComponent},
    {path: 'appdeny',component: AppdenyComponent}

  
   

];