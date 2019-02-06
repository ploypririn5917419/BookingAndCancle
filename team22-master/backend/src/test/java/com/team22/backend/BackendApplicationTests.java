package com.team22.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BackendApplicationTests{

	@Autowired
    private BookingRepository bookingrepository;
    @Autowired
    private BookingCancleRepository bookingCancleRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCategoryCannotBeNull() {
        Booking b = new Booking();
        b.setCategory(null);
               
        try {
            entityManager.persist(b);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testCategoryPattern(){
        Booking b = new Booking();
        b.setCategory("กกกกกกก");
        try {
            entityManager.persist(b);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testCategorySize(){
        Booking b = new Booking();
        b.setCategory("งาน");
        try {
            entityManager.persist(b);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
    public void testCategoryTrue(){
        Booking b = new Booking();
        b.setCategory("งานวันเด็ก");
        try {
            entityManager.persist(b);
            entityManager.flush();

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
	public void testbookingCancleReasonCannot() {
		BookingCancle bc = new BookingCancle();
		bc.setBookingCancleReason("เพราะไม่มีเงินจ่ายค่าจอง");

		try {
            entityManager.persist(bc);
            entityManager.flush();
            
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ----------------->> 1.1 Test BookingCancleReason ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
    }
    //ทดสอบข้อมูล ไม่ null
	@Test
	public void testbookingCancleReasonCannotBeNull() {
		BookingCancle bc = new BookingCancle();
		bc.setBookingCancleReason(null);

		try {
            entityManager.persist(bc);
            entityManager.flush();
            fail("Test BookingCancleReason Cannot Be Null");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ----------------->> 1. Test BookingCancleReason Cannot Be Null ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
    }
    //ทดสอบSizeMIN
    @Test
	public void testbookingCancleReasonSizeMIN() {
		BookingCancle bc = new BookingCancle();
		bc.setBookingCancleReason("ds");

		try {
            entityManager.persist(bc);
            entityManager.flush();
            fail("Test BookingCancleReason Size MIN");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println(); 
            System.out.println(e+"  ----------------->> 2. Test BookingCancleReason Size MIN");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
	}
    //ทดสอบSizeMAX
    @Test
	public void testbookingCancleReasonSizeMAX() {
		BookingCancle bc = new BookingCancle();
		bc.setBookingCancleReason("vdifjws;iv'djg'prjobsdmv'sdvmk'dvdakanfad'fnksa'afsnfaslkfnafleac ecicmf,efef");

		try {
            entityManager.persist(bc);
            entityManager.flush();
            fail("Test BookingCancleReason Size MAX");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ----------------->> 3. Test BookingCancleReason Size MAX ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
	}
	
	@Test
	public void TestbookingCancleReasonCannotBePatten() {
		BookingCancle bc = new BookingCancle();
		bc.setBookingCancleReason("กกดก");

		try {
            entityManager.persist(bc);
            entityManager.flush();
            fail("Test BookingCancleReason Cannot Be Pattern");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ----------------->> 4.Test BookingCancleReason Cannot Be Pattern");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
	}

	//ทดสอบ BookingCancleReason ไม่เหมือนกัน
	@Test
    public void TestUniqeBookingCancleReasonError() {
        BookingCancle bc1 = new BookingCancle();
		bc1.setBookingCancleReason("เพราะไม่ว่างตามเวลาจอง");
		entityManager.persist(bc1);
		entityManager.flush();
		
        BookingCancle bc2 = new BookingCancle();
		bc2.setBookingCancleReason("เพราะไม่ว่างตามเวลาจอง");
		
    try { 
        entityManager.persist(bc2);
        entityManager.flush();
        
    } catch(javax.persistence.PersistenceException e) {
        System.out.println();
        System.out.println();
        System.out.println(e+" ----------------->>5. Test Uniqe BookingCancleReason Error");
        System.out.println();
        System.out.println();
    }
}
}