import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GetEmployeeDetailsService } from '../Services/get-employee-details.service';


@Component({
  selector: 'app-user-details',
  imports: [],
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent {

  employeeId: string | null=  "";
  userDetails : any
  
  constructor(private route: ActivatedRoute , private getEmployeeService: GetEmployeeDetailsService) { }

  ngOnInit(): void {
    
    this.employeeId = this.route.snapshot.paramMap.get('id');

    this.getEmployeeService.getEmployeeDetailsById(this.employeeId).subscribe(
      (response: any) => {
        this.userDetails = response.data; // Store the actual response in userDetails
        console.log("User Deatils", this.userDetails); // Proper logging to see the data in the console
      },
      (error) => {
        console.error("Error fetching employee details:", error); // Handle errors gracefully
      }
    );
  }
} 
