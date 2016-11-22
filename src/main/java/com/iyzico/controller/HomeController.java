package com.iyzico.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World32323") String name, Model model) {
        model.addAttribute("name", name);
        return "task";
    }
	
	@RequestMapping(value="/loginPage")
	public String homePage() {
 		return "login";
 	} 
	
	
	 @RequestMapping(value="new-event",method=RequestMethod.GET)
	    public String addBookInfo(){
	 
	 
	        return "new-task";
	    }
	 
	 
	    @RequestMapping(value="/delete",method= RequestMethod.GET,produces = MediaType.ALL_VALUE)
	    public String deleteEvent(@RequestParam(value="eventId") Long eventId, RedirectAttributes rd){
	 
	        eventService.deleteEvent(eventId);
	        rd.addFlashAttribute("success",1);
	        return "redirect:/events/list";
	    }
	 
	 
	    @RequestMapping(value="/update",method= RequestMethod.POST,produces = MediaTypes.TEXT_HTML)
	    public String updateEvent(@RequestParam Long eventId,
	                               @RequestParam(required = false) String image,
	                               @RequestParam(required = false) String title,
	                               @RequestParam(required = false) String address,
	                               @RequestParam(required = false) Integer minAge,
	                               @RequestParam(required = false) Integer maxAge,
	                               @RequestParam(required = false) Double lat,
	                               @RequestParam(required = false) Double lon,
	                               @RequestParam(required = false) String  details,
	                               @RequestParam(required = false) String type,
	                               @RequestParam(required = false) Integer city,
	                               @RequestParam(required = false)/*@DateTimeFormat(pattern = "dd.MM.yyyy")*/String time,
	                               @RequestParam(required = false)/*@DateTimeFormat(pattern = "dd.MM.yyyy")*/String dateForOrder){
	 
	 
	 
	 
	 
	        Event event = eventService.getEvent(eventId);
	 
	        eventService.updateEvent(event, title, address, image, details, time, type, city, minAge, maxAge, lat, lon, dateForOrder);
	        return "redirect:/events/list";
	        //return "edit-event";
	    }
	
	
	
	
}
