package com.example.demo.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Exception.NotfoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class Employeecontroller {
	@Autowired
	private EmployeeRepo employeerepo;
	//inorder to get all emp details
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeerepo.findAll();
	}
	//create empployee rest spi
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeerepo.save(employee);
	}
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee >getEmployeebyID(@PathVariable Long id) {
	 Employee employee =employeerepo.findById(id)
			 .orElseThrow(()-> new NotfoundException("Employee not exist wit id:"+id));
	 return ResponseEntity.ok(employee);
	}
	//update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails)
	{
		Employee employee=employeerepo.findById(id)
				.orElseThrow(()-> new NotfoundException("Employee not exist wit id:"+id));
		employee.setFname(employeeDetails.getFname());
		employee.setLname(employeeDetails.getLname());
		employee.setEmail(employeeDetails.getEmail());
		Employee updatedEmployee=employeerepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>>deleteEmployee(@PathVariable Long id){
	Employee employee=employeerepo.findById(id)
			.orElseThrow(()-> new NotfoundException("Employee not exist wit id:"+id));
	 employeerepo.delete(employee);
	 Map<String,Boolean> response =new HashMap<>();
	 response.put("deleted",Boolean.TRUE);
	 return ResponseEntity.ok(response);
	}
	
}

