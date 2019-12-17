package ru.demin.itprom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.itprom.exceptions.DepartmentUseAsParent;
import ru.demin.itprom.exceptions.DepartmentUseInEmployeeException;
import ru.demin.itprom.jpa.entities.Department;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.repositories.DepartmentRepository;
import ru.demin.itprom.services.interfaces.DepartmentService;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) throws DepartmentUseInEmployeeException, DepartmentUseAsParent {
        List<Employee> employeesByDepartment = employeeService.getAllEmployeesByDepartmentId(id);
        if (employeesByDepartment.size()>0) throw new DepartmentUseInEmployeeException("Отдел используется у сотрудников", employeesByDepartment);
        List<Department> departmentChild = departmentRepository.getAllByParentId(id);
        if (departmentChild.size()>0) throw new DepartmentUseAsParent("Отдел используется, как родительский", departmentChild);
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getPossibleParentDepartments(Long id) {
        List<Department> departments = departmentRepository.getAllByIdNot(id);
        List<Department> departmentsForExclude = departmentRepository.getAllByParentId(id);

        Deque<Department> departmentDequeForExclude = new ArrayDeque();
        departmentDequeForExclude.addAll(departmentsForExclude);
        while(!departmentDequeForExclude.isEmpty()) {
            Department d = departmentDequeForExclude.pop();
            List<Department> childForExclude = departmentRepository.getAllByParentId(d.getId());
            departmentsForExclude.addAll(childForExclude);
            departmentDequeForExclude.addAll(childForExclude);
        }
        departments.removeAll(departmentsForExclude);
        return departments;
    }
}
