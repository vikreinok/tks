package ee.ttu.catering.rest.controller.rest;


import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.MenuCommentService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(name = "MenuComment service", description = "Services for managing diner menu comments", group = "Diner menu")
@RestController
@RequestMapping(value="/rest/menu_comment")
public class MenuCommnetController {

	@Autowired
	private MenuCommentService menuCommentService;
	
	@ApiMethod(description="Returns all menu comments")
	@ApiAuthNone
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<MenuComment> readAll() {
		return menuCommentService.findAll();
	}
	
}
