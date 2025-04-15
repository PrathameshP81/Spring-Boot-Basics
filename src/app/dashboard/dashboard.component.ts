import { Component } from '@angular/core';
import { GetEmployeeDetailsService } from '../Services/get-employee-details.service'; // Correct import

import { CommonModule } from '@angular/common';
import { ResponseInterface } from '../Interfaces/responseInterface';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  standalone: true, // This makes the component standalone
  imports: [CommonModule , RouterLink], // Any other modules can be added here if necessary
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})


export class DashboardComponent {
  todaysDate = '';
  employeeDetails : any [] = []
  employeeCount: any = 0;
  departmentCount : any = 0

  constructor(private getEmployeeService: GetEmployeeDetailsService) {
    this.todaysDate = new Date().toLocaleDateString();
    
    this.getEmployeeData();
    this.getEmployeeCount();
    this.getDepartmentCount();
  }

  getEmployeeData(){
     return this.getEmployeeService.getEmployeeDetails().subscribe((data:any) => {
      this.employeeDetails = data;
    });
  }

  getDepartmentCount(){
    this.getEmployeeService.getDepartmentCount().subscribe((count : any)=>{
      this.departmentCount = count
    });
  }
  
  getEmployeeCount(){
    return this.getEmployeeService.getEmployeeCount().subscribe((count : any)=>{
      this.employeeCount = count
    })
  }

  deleteEmployee(id: number) {
    this.getEmployeeService.deleteEmploeeDetails(id).subscribe(
      (response : ResponseInterface) => {

        if(response.success){
            this.getEmployeeData();
            this.getEmployeeCount();
            alert("Employee Deleted Successfully")
        }
      },
      (error) => {
        console.error('Error deleting employee:', error);
      }
    );
  }
  
}
