package com.tekpy.leave.repository;

import com.tekpy.leave.entity.LeaveRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveRequestRepository {

    private final EntityManagerFactory factory;

    public LeaveRequestRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void save(LeaveRequest request) {

        EntityManager em =
                factory.createEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(request);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }

    public List<LeaveRequest> findByEmployeeId(
            Long employeeId) {

        EntityManager em =
                factory.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT r FROM LeaveRequest r " +
                            "WHERE r.employee.id = :employeeId " +
                            "ORDER BY r.id DESC",
                            LeaveRequest.class)
                    .setParameter(
                            "employeeId",
                            employeeId)
                    .getResultList();

        } finally {

            em.close();
        }
    }

    public long countByEmployeeAndStatus(
            Long employeeId,
            String status) {

        EntityManager em =
                factory.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT COUNT(r) " +
                            "FROM LeaveRequest r " +
                            "WHERE r.employee.id = :employeeId " +
                            "AND r.status = :status",
                            Long.class)
                    .setParameter(
                            "employeeId",
                            employeeId)
                    .setParameter(
                            "status",
                            status)
                    .getSingleResult();

        } finally {

            em.close();
        }
    }
}
