import { HttpClient } from '@angular/common/http'; // Correct import
import { Injectable } from '@angular/core';
import { Observable

 } from 'rxjs';

 import { ResponseInterface } from '../Interfaces/responseInterface';
@Injectable({
  providedIn: 'root' // This makes the service available globally
})
export class GetEmployeeDetailsService {

  constructor(private http: HttpClient) {
    console.log("Get Employee Service Initialized");
  }

  getEmployeeDetails() {
    const url = "http://localhost:3000/Employee/getAllEmployee"; // Adjust the API URL
    return this.http.get(url); // Correct usage of HttpClient
  }
  
  getEmployeeCount() {
    const url = "http://localhost:3000/metadata/getEmployeeCount"
    return this.http.get(url)
  }
  getDepartmentCount(){
    const url = "http://localhost:3000/metadata/getDepartmentCount"
    return this.http.get(url)
  }

  deleteEmploeeDetails(id: number): Observable<ResponseInterface> {
    const url = `http://localhost:3000/Employee/delete/${id}`;
    return this.http.delete<ResponseInterface>(url);
  }
  getEmployeeDetailsById(id : string | null){
    const url = `http://localhost:3000/Employee/getEmployeeById/${Number(id)}`;
    return this.http.get(url);
  }
}
