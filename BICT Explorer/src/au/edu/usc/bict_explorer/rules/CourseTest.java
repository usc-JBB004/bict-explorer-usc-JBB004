package au.edu.usc.bict_explorer.rules;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

	private Course course1;
	private Course course2;
	
	private Map<String, Option> courses;
	
	PreReqs pre1 = new PreReqs("ICT112");
    PreReqs pre2 = new PreReqs("");
	
	@BeforeEach
    void setup() {
		course1=new Course("SubA","Sub-option A","AAAA","1",pre1, new Option("SubA", "Sub-option A", "AAAA"));
		course2=new Course("SubB","Sub-option B","BBBBB","3",pre2,new Option("SubB", "Sub-option B", "BBBBB"),new Option("SubC", "Sub-option C", "CCCCCC"));
	    courses = new HashMap<>();
        courses.put("ICT112", new Option("ICT112", "Python", "Learn Python"));
        courses.put("ICT115", new Option("ICT115", "Design", "System Design"));
  
    }
	
	@Test
	void testIsOfferedSem1() {
		 assertTrue(course1.isOffered(1));
		
	}

	@Test
	void testIsOfferedSem4() {
		 assertFalse(course1.isOffered(2));
	}
	
	@Test
	void testIsOfferedSem3() {
		 assertTrue(course2.isOffered(3));
	}
	
	  @Test
	    void testEmpty() {
	        assertTrue(pre2.isSatisfied(courses));
	    }

	    @Test
	    void testSingleMissing() {
	        PreReqs pre = new PreReqs("ICT113");
	        assertFalse(pre.isSatisfied(courses));
	    }

	    @Test
	    void testSingleDone() {
	        assertTrue(pre1.isSatisfied(courses));
	    }

	    @Test
	    void testTwoDone() {
	        PreReqs pre = new PreReqs("ICT112,ICT115");
	        assertTrue(pre.isSatisfied(courses));
	    }

	    @Test
	    void testTwoFirst() {
	        PreReqs pre = new PreReqs("ICT112,ICT116");
	        assertFalse(pre.isSatisfied(courses));
	    }

	    @Test
	    void testTwoSecond() {
	        PreReqs pre = new PreReqs("ICT111,ICT112");
	        assertFalse(pre.isSatisfied(courses));
	    }

	@Test
	void testGetPreReqs() {
		  assertEquals(pre1, course1.getPreReqs());
		  assertEquals(pre2, course2.getPreReqs());
	}

	@Test
	void testGetSemesters() {
		assertEquals("1", course1.getSemesters());
		  assertEquals("3", course2.getSemesters());
	}

}
