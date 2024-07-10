package com.example.expensemanagersoap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import soap.demo.web_service_test.Currency;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Expense {
    @Id
    private String id;
    private double amount;
    private String description;
    private LocalDate date;
    private Currency currency;
}