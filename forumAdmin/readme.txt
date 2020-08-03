
									FORUM ADMIN SERVICE

//----------------------------------------------------------------------------------------------------------------------------------
   Zuul Proxy SERVICE-ID/PATH										- FORUM-ADMIN
//----------------------------------------------------------------------------------------------------------------------------------
   1. AdminController												- PORT: 8091

//----------------------------------------------------------------------------------------------------------------------------------
	 SERVICE OPERATIONS
//----------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/", method = RequestMethod.GET) 		- return "Welcome to Forum Application - Admin Service.";
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/admin", method = RequestMethod.GET)	- return "Welcome to Forum Application - Admin Service : 
									  								  This is an Admin privilege to add 'Categories'.";
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value = "/admin/addCategory") 										- ResponseEntity<Boolean> 
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/admin/deleteCategory/{id}") 							- ResponseEntity<Boolean> 
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/admin/getCategoryById/{categoryId}") 						- ResponseEntity<CategoryDto> 
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/admin/getAllCategories", produces = "application/json") 	- ResponseEntity<List<CategoryDto>> 

//---------------------------------------------------------------------------------------------------
	SEARCH OPERATIONS
//---------------------------------------------------------------------------------------------------
//	@GetMapping(value = "/search/category/{categoryId}", produces = "application/json") - ResponseEntity<CategoryDto> 
