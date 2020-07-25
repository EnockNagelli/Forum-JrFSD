package com.iiht.forumAdmin.boundaryTestCases;

import static com.iiht.forumAdmin.Utils.TestUtils.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.forumAdmin.Utils.MasterData;
import com.iiht.forumAdmin.dto.CategoryDto;

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
	public void testCategorySuccess() throws Exception {
		
		CategoryDto details = MasterData.getCategoryDetails();
		Set<ConstraintViolation<CategoryDto>> violations = validator.validate(details);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 2 --------------------------------------------------------------------------------------
	@Test
	public void testAdminNameLength() throws Exception {

		CategoryDto user = MasterData.getCategoryDetails();
		user.setAdminName("an");
		Set<ConstraintViolation<CategoryDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 3 --------------------------------------------------------------------------------------
	@Test
	public void testCategoryLength() throws Exception {
		CategoryDto user = MasterData.getCategoryDetails();
		user.setCategory("ct");
		Set<ConstraintViolation<CategoryDto>> violations = validator.validate(user);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}
}