import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminComponent } from './admin/admin.component';
import { UserDetailsComponent } from './user-details/user-details.component';

export const routes: Routes = [
    {path :'' , component : DashboardComponent},
    {path : 'admin' , component : AdminComponent},
    { path: 'employee/:id', component: UserDetailsComponent }

];
