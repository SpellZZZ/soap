package com.example.expensemanagersoap.endpoint;

import com.example.expensemanagersoap.repo.ExpenseRepo;
import com.example.expensemanagersoap.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.demo.web_service_test.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
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
        Optional<Expense> optionalExpense = expenseRepo.findById(String.valueOf(request.getId()));

        if (optionalExpense.isPresent()) {
            response.setExpense(optionalExpense.get());
        } else {
            response.setExpense(new Expense());
        }

        return response;
    }



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateExpenseRequest")
    @ResponsePayload
    public CreateExpenseResponse createExpense(@RequestPayload CreateExpenseRequest request) {
        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate().toString());
        expense.setCurrency(request.getCurrency());

        Expense savedExpense = expenseRepo.save(expense);

        CreateExpenseResponse response = new CreateExpenseResponse();
        response.setId(savedExpense.getId());
        response.setAmount(savedExpense.getAmount());
        response.setDescription(savedExpense.getDescription());
        //response.setDate(savedExpense.getDate());
        response.setCurrency(savedExpense.getCurrency());

        return response;
    }

}