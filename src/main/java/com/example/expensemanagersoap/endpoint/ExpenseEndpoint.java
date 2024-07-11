package com.example.expensemanagersoap.endpoint;

import com.example.expensemanagersoap.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.demo.web_service_test.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;
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
        response.setDate(request.getDate()); // requeat there
        response.setCurrency(savedExpense.getCurrency());

        return response;
    }




    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllExpensesRequest")
    @ResponsePayload
    public GetAllExpensesResponse getAllExpenses(@RequestPayload GetAllExpensesRequest request) throws DatatypeConfigurationException {
        GetAllExpensesResponse response = new GetAllExpensesResponse();
        List<Expense> expenses = expenseRepo.findAll();
        for (Expense expense : expenses) {
            Expense expenseXml = new Expense();
            expenseXml.setId(expense.getId());
            expenseXml.setAmount(expense.getAmount());
            expenseXml.setDescription(expense.getDescription());
            expenseXml.setDate(expense.getDate());
            expenseXml.setCurrency(expense.getCurrency());
            response.getExpenses().add(expenseXml);
        }
        return response;
    }

}