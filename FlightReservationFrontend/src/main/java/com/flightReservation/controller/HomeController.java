package com.flightReservation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.flightReservation.model.Booking;
import com.flightReservation.model.City;
import com.flightReservation.model.Flight;
import com.flightReservation.model.Payment;
import com.flightReservation.model.SeatCategory;
import com.flightReservation.model.User;
import com.flightReservation.repository.BookingRepository;
import com.flightReservation.repository.CityRepository;
import com.flightReservation.repository.FlightRepository;
import com.flightReservation.repository.PaymentRepository;
import com.flightReservation.service.BookingService;
import com.flightReservation.service.CityService;
import com.flightReservation.service.FlightService;
import com.flightReservation.service.PaymentService;
import com.flightReservation.service.UserService;

@Controller
public class HomeController {
	@Autowired
    UserService userService;
	@Autowired
	CityService cityService;
	@Autowired
	FlightService flightService;
	@Autowired
	BookingService bookingService;
	@Autowired
	PaymentService paymentService;

	
	User userloggedin;
	
	@GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
		/*int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			System.out.println("try"+userId);
		} catch (NullPointerException npe) {
			System.out.println("catch");
			return "index";
		}
		if(userId!=0) {
			model.addAttribute("isLogged", "======");
		}*/
        return "home";
    }
	
	@GetMapping("/login")
    public String loginForm(Model model) {
        return "auth";
    }

    @PostMapping("/login")
    public String loginSubmit(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            boolean logged = userService.login(username, password);
            if(logged==true) {
                System.out.println("login");
                User user = userService.findByUsername(username);
                request.getSession().setAttribute("userId", user.getUser_id());
                return "redirect:/index";}
            else {
                System.out.print("unlogin");
                return "auth";}
        }
        catch (Exception e) {
            return "auth";
        }
    }
    
    @RequestMapping("/index")
	public String index(HttpServletRequest request, Model model)
	{	
    	//consume API get all city
    	RestTemplate restTemp = new RestTemplate();
    	List<LinkedHashMap<String, Object>> cityMap = restTemp.getForObject("http://localhost:8081/api-city", List.class);
    	List<City> listCity = new ArrayList<>();
    	//mapping object list city to model city
    	for(LinkedHashMap<String, Object> map : cityMap){
    		listCity.add(new City((int)map.get("city_id"), (String)map.get("name")));
   		}
    	
    	//consume API get all city
    	RestTemplate restTemplate = new RestTemplate();
    	List<LinkedHashMap<String, Object>> seatCategoryMap = restTemplate.getForObject("http://localhost:8081/api-seat", List.class);
    	List<SeatCategory> listSeatCategory = new ArrayList<>();
    	//mapping object list city to model city
    	for(LinkedHashMap<String, Object> map : seatCategoryMap){
    		listSeatCategory.add(new SeatCategory((int)map.get("seatId"), (String)map.get("seatName")));
   		}
    	
    	model.addAttribute("seatCategory", listSeatCategory);
		model.addAttribute("city", listCity);
		return "index";
	}

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
    	int userId = 0;
    	try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
	    	System.out.println("id logout : " +userId);
	    	request.getSession().removeAttribute("userId");
	        return "redirect:/";
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerSubmit(ModelAndView modelAndView, @Valid User user,
                                        BindingResult bindingResult, HttpServletRequest request) {

        // Lookup user in database by e-mail
        User userExists =  null;
        try {
            System.out.print(user.getUsername());
            System.out.print("==================================");
            userExists = userService.findByUsername(user.getUsername());
            System.out.println(userExists);
        }
        catch (Exception e){

        }
        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the username provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("username");
        } else {
            userService.save(user);
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
    
	@GetMapping("/search")
	public String search( HttpServletRequest request, Model model) throws ParseException {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		
		int departure = Integer.valueOf(request.getParameter("departure"));
		int destination = Integer.valueOf(request.getParameter("destination"));
		int categorySeat = Integer.valueOf(request.getParameter("categorySeat"));
		String departureDate = request.getParameter("departureDate");
		
		//consume API get all flight
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> flightsMap = restTemplate.getForObject("http://localhost:8081/api-flight", List.class);
		List<Flight> listFlight = new ArrayList<>();
		//mapping object list flight to model flight
		for(LinkedHashMap<String, Object> map : flightsMap){
			String string = (String) map.get("departure_date");
			String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
			Date date = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(string);
			listFlight.add(new Flight((int)map.get("flightId"), (String)map.get("airline"), (String)map.get("departure_city"),
										(String)map.get("destination_city"), (int)map.get("price"), (String)map.get("categorySeat"), 
										date));
		}
		System.out.println("listFlight : " + listFlight.size());
		
		//consume API get all city
		RestTemplate restTemp = new RestTemplate();
		List<LinkedHashMap<String, Object>> cityMap = restTemp.getForObject("http://localhost:8081/api-city", List.class);
		List<City> listCity = new ArrayList<>();
		//mapping object list city to model city
		for(LinkedHashMap<String, Object> map : cityMap){
			listCity.add(new City((int)map.get("city_id"), (String)map.get("name")));
		}
		
		//consume API get all city
    	RestTemplate rest = new RestTemplate();
    	List<LinkedHashMap<String, Object>> seatCategoryMap = rest.getForObject("http://localhost:8081/api-seat", List.class);
    	List<SeatCategory> listSeatCategory = new ArrayList<>();
    	//mapping object list city to model city
    	for(LinkedHashMap<String, Object> map : seatCategoryMap){
    		listSeatCategory.add(new SeatCategory((int)map.get("seatId"), (String)map.get("seatName")));
   		}
		
		String departureTemp = listCity.get(departure-1).getName();
		System.out.println("departure : " +departureTemp);
		String destinationTemp = listCity.get(destination-1).getName();
		System.out.println("destination : " + destinationTemp);
		String categoryTemp = listSeatCategory.get(categorySeat-1).getSeatName();
		System.out.println("category : " + categoryTemp);
		
		List<Flight> listFlightTemp = new ArrayList<>();
		for (Flight flight : listFlight){
			if (flight.getDeparture_city().equalsIgnoreCase(departureTemp) && flight.getDestination_city().equalsIgnoreCase(destinationTemp) && 
					flight.getCategorySeat().equalsIgnoreCase(categoryTemp)) {
				listFlightTemp.add(flight);
			}
		}

		model.addAttribute("allflight",listFlightTemp);
        return "search";
    }
	
	@RequestMapping("flight/{id}")
	public String showFlight(@PathVariable Integer id, Model model, HttpServletRequest request) {
		//consume API get flight by id
		RestTemplate restTemplate =  new RestTemplate();
		Flight flight = restTemplate.getForObject("http://localhost:8081/api-flight/"+id, Flight.class);
		model.addAttribute("flight", flight);
		request.getSession().setAttribute("choosenFlight", id);
		request.getSession().setAttribute("choosenPrice", flight.getPrice());
		request.getSession().setAttribute("choosenAirline", flight.getAirline());
		
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			System.out.println("id-flight : " + userId);
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		
		return "detail";
	}
	
	@RequestMapping("/contact") 
	public String contact(HttpServletRequest request) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		return "contact";
	}
	
	@RequestMapping("/passenger")
	public String passenger(Model model, HttpServletRequest request) {
		String name = request.getParameter("name");
		int phone = Integer.valueOf(request.getParameter("phone"));
		String email = request.getParameter("email");
		int noPassenger = Integer.valueOf(request.getParameter("passenger"));
		
		List<String> listPassenger = new ArrayList<String>();
		for(int i=0; i<=noPassenger-1; i++) {
			String title = request.getParameter("title"+i);
			String passenger = request.getParameter("passenger"+i);
			listPassenger.add(title+" "+passenger);
		}
		String listPassengerName = listPassenger.toString().substring(1, listPassenger.toString().length()-1);
		
		int choosenFlight = 0;
		try {
			choosenFlight = Integer.valueOf(request.getSession().getAttribute("choosenFlight").toString());
		}
		catch (NullPointerException npe) {
			return "redirect:/search";
		}
		
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		
		int choosenPrice = 0;
		try {
			choosenPrice = Integer.valueOf(request.getSession().getAttribute("choosenPrice").toString());
		} catch (NullPointerException npe) {
			return "redirect:/search";
		}
		
		String choosenAirline;
		try {
			choosenAirline = (String) request.getSession().getAttribute("choosenAirline");
		} catch (NullPointerException npe) {
			return "redirect:/search";
		}
		
		int totalPrice = noPassenger * choosenPrice;
		request.getSession().setAttribute("totalPrice", totalPrice);
		
		List<Booking> listBooking = bookingService.findAll();
		int id = listBooking.get(listBooking.size()-1).getBooking_id();
		
		Booking booking = new Booking();
		booking.setFull_name(name);
		booking.setPhone(phone);
		booking.setEmail(email);
		booking.setFlight_id(choosenFlight);
		booking.setUser_id(userId);
		booking.setListPassengerName(listPassengerName);
		
		request.getSession().setAttribute("booking", booking);
		request.getSession().setAttribute("bookingId", id+1);
		
		model.addAttribute("booking",booking);
		model.addAttribute("allpassanger",listPassengerName.split(", "));
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("choosenAirline", choosenAirline);
		return "passenger";
	}
	
	@RequestMapping("/bookingResult")
	public String bookingResult(Model model, HttpServletRequest request) {
		Booking booking;
		Flight choosenFlight;
		try {
			booking = (Booking) request.getSession().getAttribute("booking");
			choosenFlight = flightService.findById(booking.getFlight_id());
			flightService.save(choosenFlight);
			bookingService.save(booking);
		} catch (NullPointerException npe) {
			return "redirect:/";
		}
		
		int totalPrice = 0;
		try {
			totalPrice = Integer.valueOf(request.getSession().getAttribute("totalPrice").toString());
		} catch (NullPointerException npe) {
			return "redirect:/";
		}
		
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		
		int bookingId = 0;
		try {
			bookingId = Integer.valueOf(request.getSession().getAttribute("bookingId").toString());
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		
		String booking_id = String.valueOf(booking.getBooking_id());
		String flight_id = String.valueOf(choosenFlight.getFlightId());
		String user_id = String.valueOf(userId);
		byte[] bytesEncoded = Base64.getEncoder().encode((booking_id+flight_id+user_id).getBytes());
		String token = new String(bytesEncoded);
		
		Payment payment = new Payment();
		payment.setToken(token);
		payment.setToken(token);
		payment.setBooking_id(bookingId);
		payment.setTotalPrice(totalPrice);
		payment.setUser_id(userId);
		paymentService.save(payment);
		
		//send payment using API create payment
		RestTemplate rt = new RestTemplate();
		//backend
		Payment res = rt.postForObject("http://localhost:8081/api-create-payment", payment, Payment.class);
		//IB
		//Payment result = rt.postForObject("http://", payment, Payment.class);
		
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("token", token);
		return "bookingResult";
	}
	
	@RequestMapping("/checkpayment")
	public String checkpayment() {
		return "checkpayment";
	}
	
	@RequestMapping("/searchBooking")
	public String searchBooking (HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		List<Payment> listPayment = paymentService.findAll();
		for (Payment payment : listPayment) {
			if (payment.getToken().equalsIgnoreCase(token)) {
				int bookingId = payment.getBooking_id();
				Booking bookingTemp = bookingService.findById(bookingId);
				model.addAttribute("payment", payment);
				model.addAttribute("booking",bookingTemp);
			}
		}
		
		return "searchBooking";
	}
	
}
