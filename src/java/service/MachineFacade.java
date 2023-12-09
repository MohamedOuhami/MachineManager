/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author v01d
 */
@Stateless
public class MachineFacade extends AbstractFacade<Machine> {

    @PersistenceContext(unitName = "MachineManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MachineFacade() {
        super(Machine.class);
    }
    
    // Add the new method to fetch machines by employee
    public List<Machine> findByEmployeeId(int employeeId) {
        TypedQuery<Machine> query = em.createNamedQuery("Machine.findByEmploye", Machine.class);
        query.setParameter("employe_id", employeeId);
        return query.getResultList();
    }
}
