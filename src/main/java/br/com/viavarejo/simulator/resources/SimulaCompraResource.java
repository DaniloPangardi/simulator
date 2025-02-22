package br.com.viavarejo.simulator.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viavarejo.simulator.dto.SimulaCompra;
import br.com.viavarejo.simulator.service.SimulaCompraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/simula/compra")
public class SimulaCompraResource {
	
	@Autowired
	private SimulaCompraService simulaCompraService;
	
	@ApiOperation(value = "Simula a compra de um produto")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de parcelas"),
	    @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
	})
	@PostMapping
	public ResponseEntity<?> simulaCompra(@Valid @RequestBody SimulaCompra request, Errors errors) throws Exception {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		return ResponseEntity.ok().body(simulaCompraService.simular(request));
	}

}
