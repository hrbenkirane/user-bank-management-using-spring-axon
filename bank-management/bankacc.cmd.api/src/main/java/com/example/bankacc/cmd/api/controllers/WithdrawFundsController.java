package com.example.bankacc.cmd.api.controllers;

import com.example.bankacc.cmd.api.commands.DepositFundsCommand;
import com.example.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.example.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/withrawFunds")
public class WithdrawFundsController {
    private CommandGateway commandGateway;

    @Autowired
    public WithdrawFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody WithdrawFundsCommand command) {
        try{
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("successfully withdrawd funds from bank account"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing request to withdraw funds from bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

