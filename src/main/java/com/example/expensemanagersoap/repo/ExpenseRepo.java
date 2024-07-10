package com.example.expensemanagersoap.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soap.demo.web_service_test.Expense;

@Repository
public interface ExpenseRepo extends MongoRepository<Expense,Long> {



}