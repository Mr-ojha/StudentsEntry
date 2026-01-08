package com.witsinnovationlab.MyRESTApi.service.impl;

import com.witsinnovationlab.MyRESTApi.dto.AddStudentRequestdto;
import com.witsinnovationlab.MyRESTApi.dto.StudentDto;
import com.witsinnovationlab.MyRESTApi.entity.Student;
import com.witsinnovationlab.MyRESTApi.repository.StudentRepository;
import com.witsinnovationlab.MyRESTApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoslist = students
                .stream()
                .map(student -> modelMapper.map(students, StudentDto.class))
                .toList();
        return List.of();

    }

    @Override
    public StudentDto getStudentBYId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student Not Found with "+id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto addNewStudent(AddStudentRequestdto addStudentRequestdto) {
        Student newStudent = modelMapper.map(addStudentRequestdto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not Exist by Id: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestdto addStudentRequestdto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student does not Exist by Id: "+ id));
        modelMapper.map(addStudentRequestdto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> update) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student does not Exist by Id: "+ id));
        update.forEach((field, value)->{
            switch (field){
                case "name" : student.setName((String) value);
                break;
                case "email": student.setEmail((String) value);
                break;
                default:
                    throw new IllegalArgumentException("field is not Supported");
            }
                });
        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent, StudentDto.class);
    }
}
