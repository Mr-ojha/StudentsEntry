package com.witsinnovationlab.MyRESTApi.service;

import com.witsinnovationlab.MyRESTApi.dto.AddStudentRequestdto;
import com.witsinnovationlab.MyRESTApi.dto.StudentDto;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface StudentService {

     List<StudentDto> getAllStudents();

    StudentDto getStudentBYId(Long id);

     StudentDto addNewStudent(AddStudentRequestdto addStudentRequestdto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestdto addStudentRequestdto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> update);
}
