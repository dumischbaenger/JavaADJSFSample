package de.dumischbaenger.dbtools;

import javax.enterprise.context.SessionScoped
import javax.inject.Inject
import javax.inject.Named
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence

import de.dumischbaenger.domainmodel.Customer


@SessionScoped
@Named("dbCustomerService")
public class DbCustomerService implements Serializable {

  @Inject
  DbAccess dbAccess
  
  public DbCustomerService() {
    super();
  }
  
  List searchCustomer(Map searchCriteria) {
//    log.info("search criteria: $searchCriteria")
    List customer=[]

    String query="select c from Customer c where 1=1 "
    searchCriteria.each{
      key, value ->
      if(value) {
        switch (value) {
          case String:
            query+= " and $key like '${value}%'"
            break
          default:
            query+=""
        }
      }
    }
    customer=dbAccess.getEntityManager().createQuery(query).getResultList().collect()
    
//    log.info("personen: $persons")

    customer
  }
  
//  Person newPerson() {
//    log.info("new person")
//    Person p=new Person();
//    p.name="enter name"
//    p.age=99
//    p.gender=1
//
//    p
//  }
//  
  
  Customer saveCustomer(Customer c) {
//    log.info("save person: $c")
	
	println("\n\nSave customer\n\n")
	
	EntityManager entityManager=dbAccess.entityManager
    EntityTransaction tx=entityManager.getTransaction()
    tx.begin()
    entityManager.persist(c)
    tx.commit()

    c
  }
  
//  void removePerson(Person p) {
//    log.info("removePerson person: $p")
//
//    if(p!=null) {
//      entityManagerHandler.withEntityManager("exampledb"){
//        String persistenceUnitName, EntityManager entityManager ->
//        EntityTransaction tx=entityManager.getTransaction()
//        tx.begin()
//        entityManager.remove(p)
//        tx.commit()
//      }
//    }
//  
//    p
//  }

 }