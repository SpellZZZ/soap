package com.example.expensemanagersoap.endpoint;

import com.example.expensemanagersoap.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.demo.web_service_test.Expense;
import soap.demo.web_service_test.GetExpenseRequest;
import soap.demo.web_service_test.GetExpenseResponse;

import java.util.Optional;

@Endpoint
public class ExpenseEndpoint {
    private static final String NAMESPACE_URI = "http://demo.soap/web-service-test";

    final private ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseEndpoint(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExpenseRequest")
    @ResponsePayload
    public GetExpenseResponse getExpense(@RequestPayload GetExpenseRequest request) {
        GetExpenseResponse response = new GetExpenseResponse();
        Optional<Expense> optionalExpense = expenseRepo.findById(request.getId());

        if (optionalExpense.isPresent()) {
            response.setExpense(optionalExpense.get());
        } else {
            response = null;
        }

        return response;
    }
}