package com.farm.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farm.main.entity.SupplierEntity;
import com.farm.main.service.ISupplierService;

@RestController
@RequestMapping("/farming")
public class SupplierController {
	
	@Autowired
	private ISupplierService supplierService;

	@GetMapping("/findSupplierList")
	public ResponseEntity<List<SupplierEntity>> getAllSupplier()  {
		try {
			List<SupplierEntity> users = supplierService.getSupplierList();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(name="/getSupplierById", produces = "application/json")
	public ResponseEntity<SupplierEntity> getSupplierById(@RequestParam Long supplierId) {
		Optional<SupplierEntity> supplier = null;
		try {
			supplier = supplierService.getSupplierById(supplierId);

			if (supplier.isPresent()) {
				return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}



	@PostMapping(name = "/addSupplier", produces = "application/json")
	public ResponseEntity<SupplierEntity> addFarmer(@RequestBody SupplierEntity supplier) {
		try {
			SupplierEntity added = supplierService.addSupplier(supplier);
			return new ResponseEntity<>(supplier, HttpStatus.CREATED);// 201
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);

		}
	}
		
		@DeleteMapping("/deleteSupplier")
		public ResponseEntity<String> deleteSupplier(@RequestParam Long id) {

			try {
				supplierService.deleteSupplier(id);
				Optional<SupplierEntity> delUser = supplierService.getSupplierById(id);
				return new ResponseEntity<>("Record Deleted...with id : " + id, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("Record not found with id : " + id, HttpStatus.EXPECTATION_FAILED);
			}
		}

		@PutMapping("/updateSupplier")
		public ResponseEntity<Object> updateSupplier(@RequestBody SupplierEntity supplier, @PathVariable Long id) {
			try {
				Optional<SupplierEntity> userFound = supplierService.getSupplierById(id);

				if (userFound.isPresent()) {
					supplierService.updateSupplier(supplier);
					return ResponseEntity.ok(supplier);
				} else {
					return new ResponseEntity<>("Record NOT updated with Id : " + supplier, HttpStatus.NO_CONTENT);
				}
			} catch (Exception e) {
				return new ResponseEntity<>("Record NOT updated with Id : " + supplier, HttpStatus.EXPECTATION_FAILED);
			}

		}
	}

