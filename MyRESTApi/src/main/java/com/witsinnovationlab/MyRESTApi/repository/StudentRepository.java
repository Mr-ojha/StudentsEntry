package com.witsinnovationlab.MyRESTApi.repository;

import com.witsinnovationlab.MyRESTApi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
