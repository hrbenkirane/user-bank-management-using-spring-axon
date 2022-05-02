package com.example.bankacc.cmd.api.controllers;

import com.example.bankacc.cmd.api.commands.CloseAccountCommand;
import com.example.bankacc.cmd.api.commands.DepositFundsCommand;
import com.example.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/closeBankAccount")
public class CloseAccountController {

    private CommandGateway commandGateway;

    @Autowired
    public CloseAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeBankAccount(@PathVariable(value = "id") String id) {
        try {
            CloseAccountCommand command = CloseAccountCommand.builder().id(id).build();
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new BaseResponse("Bank account successfully closed!"), HttpStatus.OK);
        } catch (Exception e ) {
            String safeErrorMessage = "Error while processing request to close bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
