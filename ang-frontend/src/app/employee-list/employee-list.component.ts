import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {Employee} from '../employee'
import { EmployeeService } from '../employee.service';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  [x: string]: any;
  
  employees!: Employee[];
  constructor(private employeeservice:EmployeeService,
    private router:Router){}
  
  ngOnInit(): void {
    this.getEmployees();
  }
  private getEmployees()
  {
    this.employeeservice.getEmployeesList().subscribe(data=>
      {
        this.employees=data;

      });
  }
  employeeDetails(id:number){
    this.router.navigate(['employee-details',id]);
  }
  updateEmployee(id:number){
    this.router.navigate(['update-employee', id]);
  }
  deleteEmployee(id: number){
    this.employeeservice.deleteEmployee(id).subscribe( data=> {
      console.log(data);
      this.getEmployees();
    });
  }
}
