package com.programadorItaloBack.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programadorItaloBack.app.controller.models.entity.ReqCostumer;
import com.programadorItaloBack.app.models.services.IReqCostumerService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CostumerApiController {

	@Autowired
	private IReqCostumerService costumerService;
	

	@PostMapping("/reqCostumers")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ReqCostumer costumer, BindingResult result) {

		ReqCostumer costumerNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError err : result.getFieldErrors(null)) {
				errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
			}
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			costumerNew = costumerService.saveApi(costumer);

		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el INSERT en la base de datos");
			response.put("message", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "El cliente ha sido creado con éxito");
		response.put("costumer", costumerNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@GetMapping("/reqCostumers/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		ReqCostumer costumer = null;
		Map<String, Object> response = new HashMap<>();

		try {
			costumer = costumerService.findByIdApi(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("message", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (costumer == null) {
			response.put("message", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ReqCostumer>(costumer, HttpStatus.OK);
	}

}
