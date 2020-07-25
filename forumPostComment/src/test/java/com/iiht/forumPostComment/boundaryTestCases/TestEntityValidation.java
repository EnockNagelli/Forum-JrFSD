package com.iiht.forumPostComment.boundaryTestCases;

import static com.iiht.forumPostComment.Utils.TestUtils.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.forumPostComment.Utils.MasterData;
import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.dto.VisitorPostDto;

@RunWith(SpringRunner.class)
public class TestEntityValidation 
{
	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	//--- Entity : VisitorPosts -----------------------------------------------------------------
	//--- Test 1 --------------------------------------------------------------------------------
	@Test
	public void testVisitorPostSuccess() throws Exception {
		
		VisitorPostDto visitorPost = MasterData.getVisitorPostDetails();
		Set<ConstraintViolation<VisitorPostDto>> violations = validator.validate(visitorPost);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 2 --------------------------------------------------------------------------------------
	@Test
	public void testCategoryLength() throws Exception {

		VisitorPostDto visitorPost = MasterData.getVisitorPostDetails();
		visitorPost.setCategory("ct");
		Set<ConstraintViolation<VisitorPostDto>> violations = validator.validate(visitorPost);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 3 --------------------------------------------------------------------------------------
	@Test
	public void testPostTitleLength() throws Exception {
		VisitorPostDto visitorPost = MasterData.getVisitorPostDetails();
		visitorPost.setTitle("ti");
		Set<ConstraintViolation<VisitorPostDto>> violations = validator.validate(visitorPost);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//--- Test 4 --------------------------------------------------------------------------------------
	@Test
	public void testPostTagsLength() throws Exception {
		VisitorPostDto visitorPost = MasterData.getVisitorPostDetails();
		visitorPost.setTags("tg");
		Set<ConstraintViolation<VisitorPostDto>> violations = validator.validate(visitorPost);
		yakshaAssert(currentTest(),	violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//--- Entity : VisitorComments -----------------------------------------------------------------
	//--- Test 1 --------------------------------------------------------------------------------
	public void testVisitorCommentSuccess() throws Exception {

		VisitorCommentDto visitorComment = MasterData.getVisitorCommentDetails();
		Set<ConstraintViolation<VisitorCommentDto>> violations = validator.validate(visitorComment);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
	//--- Test 2 --------------------------------------------------------------------------------------
	@Test
	public void testCommentTagsLength() throws Exception {
		VisitorCommentDto visitorComment = MasterData.getVisitorCommentDetails();
		visitorComment.setTags("ts");
		Set<ConstraintViolation<VisitorCommentDto>> violations = validator.validate(visitorComment);
		yakshaAssert(currentTest(),	violations.isEmpty() ? true : false, boundaryTestFile);
	}
	//--- Entity : VisitorComments -----------------------------------------------------------------
	//--- Test 1 --------------------------------------------------------------------------------
	public void testVisitorLikeSuccess() throws Exception {
		
		VisitorLikeDto visitorLike = MasterData.getVisitorLikeDetails();
		Set<ConstraintViolation<VisitorLikeDto>> violations = validator.validate(visitorLike);
		yakshaAssert(currentTest(), violations.isEmpty()? true : false, boundaryTestFile);
	}
}