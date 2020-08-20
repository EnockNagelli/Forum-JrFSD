
								FORUM USER MANAGEMENT SERVICE

//---------------------------------------------------------------------------------------------------
   Zuul Proxy SERVICE-ID/PATH			- FORUM-USER
//---------------------------------------------------------------------------------------------------
   RegisterUserController 				- PORT: 8093
//---------------------------------------------------------------------------------------------------
   @RequestMapping (value = "/")		- return "Welcome to Forum Application - User Management Service.";
//---------------------------------------------------------------------------------------------------
   @RequestMapping (value = "/user") 	- return "Welcome to Forum Application - User Management Service : 
								  		  Register your details to avail 'Forum' Application.";
//---------------------------------------------------------------------------------------------------
   @PostMapping(value = "/user/newUser") 									- ResponseEntity<Boolean>
//---------------------------------------------------------------------------------------------------
   @DeleteMapping(value = "/user/deleteUser/{registerId}")					- ResponseEntity<Boolean>
//---------------------------------------------------------------------------------------------------
   @PostMapping(value = "/user/resetUserPassword")							- ResponseEntity<Boolean>
//---------------------------------------------------------------------------------------------------
   @GetMapping(value = "/user/getUserById/{registerId}") 					- ResponseEntity<RegisterUserDto> 
//---------------------------------------------------------------------------------------------------
   @GetMapping(value = "/user/getUserByLoginName/{loginName}")				- ResponseEntity<RegisterUserDto> 
//---------------------------------------------------------------------------------------------------
   @GetMapping(value = "/user/loginCredentials/{loginName}/{password}")		- ResponseEntity<RegisterUserDto>
//---------------------------------------------------------------------------------------------------
   @GetMapping(value = "/user/getAllUsers", produces = "application/json")	- ResponseEntity<List<RegisterUserDto>>
//---------------------------------------------------------------------------------------------------
   @GetMapping(value = "/user/getAllByRole/{role}") 						- ResponseEntity<List<RegisterUserDto>>
   