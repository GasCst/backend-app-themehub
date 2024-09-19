package com.themehub.controller;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.PurchaseDTO;
import com.themehub.dto.request.PurchaseDTORequest;
import com.themehub.errorhandler.EntityUnprocessableException;
import com.themehub.model.User;
import com.themehub.service.PurchaseService;
import com.themehub.util.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class PurchaseController {

    final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping(ThemehubConstant.RESOURCE_PURCHASES + ThemehubConstant.RESOURCE_PURCHASES_PURCHASE)
    public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid PurchaseDTORequest dto) {
            return new ResponseEntity<>(purchaseService.addPurchase(dto), HttpStatus.CREATED);
    }

    @GetMapping( ThemehubConstant.RESOURCE_PURCHASES + ThemehubConstant.RESOURCE_PURCHASES_PURCHASE )
    public List<PurchaseDTO> getPurchases(@RequestBody @Valid User user) {
        try {
            return purchaseService.getPurchasesByUser(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!", e);
        }
    }

    @GetMapping(ThemehubConstant.RESOURCE_PURCHASES
            + ThemehubConstant.RESOURCE_PURCHASES_PURCHASE
            + ThemehubConstant.RESOURCE_GENERIC_START_DATE
            + ThemehubConstant.RESOURCE_GENERIC_END_DATE
    )
    public ResponseEntity getPurchasesInPeriod(@Valid @RequestBody User user,
                                               @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                                               @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        try {
            List<PurchaseDTO> result = purchaseService.getPurchasesByUserInPeriod(user, start, end);
            if (result.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found XXX!", e);
        } catch (EntityUnprocessableException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be previous end date XXX!", e);
        }
    }



}
