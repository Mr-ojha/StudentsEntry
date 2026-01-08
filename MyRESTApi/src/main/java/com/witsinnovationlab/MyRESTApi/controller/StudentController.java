package com.witsinnovationlab.MyRESTApi.controller;

import com.witsinnovationlab.MyRESTApi.dto.AddStudentRequestdto;
import com.witsinnovationlab.MyRESTApi.dto.StudentDto;
import com.witsinnovationlab.MyRESTApi.service.StudentService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
       // return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentBYId(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> addNewStudent(@RequestBody @Valid AddStudentRequestdto addStudentRequestdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addNewStudent(addStudentRequestdto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
                                                    @RequestBody @Valid AddStudentRequestdto addStudentRequestdto){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestdto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody @Valid Map<String, Object> update){
        return  ResponseEntity.ok(studentService.updatePartialStudent(id, update));
    }
}
