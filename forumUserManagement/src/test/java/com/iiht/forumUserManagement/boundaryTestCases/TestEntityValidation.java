package com.iiht.forumUserManagement.boundaryTestCases;

import static com.iiht.forumUserManagement.Utils.TestUtils.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.forumUserManagement.Utils.MasterData;
import com.iiht.forumUserManagement.dto.RegisterUserDto;

@RunWith(SpringRunner.class)
public class TestEntityValidation 
{
	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	//--- Test 1 --------------------------------------------------------------------------------------
	@Test
	public void testRegisterUserSuccess() throws Exception {
		
		RegisterUserDto details = MasterData.getRegisterUserDetails();
		Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(details);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 2 --------------------------------------------------------------------------------------
	@Test
	public void testUserFirstNameLength() throws Exception {

		RegisterUserDto user = MasterData.getRegisterUserDetails();
		user.setFirstName("ab");
		Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 3 --------------------------------------------------------------------------------------
	@Test
	public void testLoginNameLength() throws Exception {
		RegisterUserDto user = MasterData.getRegisterUserDetails();
		user.setLoginName("ab");
		Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//--- Test 4 --------------------------------------------------------------------------------------
	@Test
	public void testUserMobileNo() throws Exception {
		RegisterUserDto user = MasterData.getRegisterUserDetails();
		user.setMobileNo(123);
		Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(),	violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//--- Test 5 --------------------------------------------------------------------------------------
	@Test
	public void testUserEmail() throws Exception {
		RegisterUserDto user = MasterData.getRegisterUserDetails();
		user.setEmail("a@b.c");
		Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}	
}