package com.example.expensemanagersoap.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import soap.demo.web_service_test.Expense;

@Repository
public interface ExpenseRepo extends ReactiveMongoRepository<Expense,String> {



}