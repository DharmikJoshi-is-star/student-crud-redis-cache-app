package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	/*
	 * this function will throw an exception if student id is not present in the database
	 * else it will successfully fetch the student information and will return it
	*/
	public Student getStudent(Long sId) throws StudentNotFoundException{
		
		return studentRepository.findById(sId)
				.orElseThrow(()-> new StudentNotFoundException("Student not found on :"+sId));
	}


	/*
	 * this function will throw an exception is student obj is null
	 * else it will success fully save the student data into DB will return the saved object
	*/
	public Student saveStudent(Student student) throws StudentNotFoundException{
		if(student == null)
			throw new StudentNotFoundException("Invalid Student Details");
		return studentRepository.save(student);
	}

	
	/*
	 * this function will throw an exception if either student detail object is null
	 * else it will return the updated value 
	*/
	public Student updateStudent(Long sId, Student studentDetails) throws StudentNotFoundException{
		
		if(studentDetails==null)
			throw new StudentNotFoundException("Invalid Student Details");
		
		Student student = studentRepository.findById(sId)
				.orElseThrow(()-> new StudentNotFoundException("Student not found on :"+sId));
		
		student.copyPropertiesExceptId(studentDetails);
		return studentRepository.save(student);
	}

	
	/*
	 * this function will throw an exception if student id not found in the DB
	 * else it will success fully delete the student object
	 */
	public Boolean deleteStudent(Long sId) throws StudentNotFoundException{
		
		if(!studentRepository.existsById(sId))
			throw new StudentNotFoundException("Student not found on :"+sId);
			
		studentRepository.deleteById(sId);
		return true;
	}

	
}
