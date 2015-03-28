package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.DinerComment;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;

@Controller
@RequestMapping(value="/diner")
public class DinerControllerImpl implements DinerController {

	@Autowired
	private DinerService dinerService;
	@Autowired
	private MenuService menuService;

	@Override
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@Override
	public List<Diner> allIntegration() {
		return dinerService.getAllIntegrationDiners();
	}
	
	@Override
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
	}
	
	@Override
	public Diner create(@Valid @RequestBody Diner diner) {
		return dinerService.create(diner);
	}
	
	@Override
	public Diner update(@PathVariable Integer id, @RequestBody Diner diner) {
		diner.setId(id);
		return dinerService.update(diner) ;
	}
	
	@Override
	public void delete(@PathVariable Integer id) {
		dinerService.delete(id);
	}
	
	@Override
	public List<Menu> findDinerMenus(@PathVariable Integer dinerId) {
		return menuService.findDinerMenus(dinerId);
	}
	
	@Override
	public List<Diner> findByName(@PathVariable String name) {
		return dinerService.findByName(name);
	}
	
	@Override
	public Diner addComment(@PathVariable int dinerId, @RequestBody @Valid DinerComment dinerComment) {
		Diner diner = dinerService.get(dinerId);
		diner.addDinerComment(dinerComment);
		
		return dinerService.update(diner);
	}

	
}
