package com.tekpy.leave.repository;

import com.tekpy.leave.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    private final EntityManagerFactory factory;

    public EmployeeRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Employee findByEmployeeId(String employeeId) {

        EntityManager em =
                factory.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT e FROM Employee e " +
                            "WHERE e.employeeId = :employeeId",
                            Employee.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();
        }
    }

    public Employee findById(Long id) {

        EntityManager em =
                factory.createEntityManager();

        try {

            return em.find(Employee.class, id);

        } finally {

            em.close();
        }
    }
}
