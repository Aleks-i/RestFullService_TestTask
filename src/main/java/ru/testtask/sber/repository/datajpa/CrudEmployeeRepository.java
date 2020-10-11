package ru.testtask.sber.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.sber.model.Employee;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT e FROM Employee e WHERE e.department.id=:departmentId ORDER BY e.dateBirthday ASC")
    List<Employee> getAllForDepartment(@Param("departmentId") int departmentId);

    @Query("SELECT e from Employee e WHERE e.department.id=:departmentId AND e.dateBirthday >= :startDate AND e.dateBirthday < :endDate ORDER BY e.dateBirthday DESC")
    List<Employee> getBetweenHalfOpen(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("departmentId") int departmentId);

    @Query("SELECT e from Employee e WHERE e.department.id=:departmentId AND e.dateBirthday = :date ORDER BY e.dateBirthday DESC")
    List<Employee> getByDate(@Param("date") LocalDate date, @Param("departmentId") int departmentId);
}
