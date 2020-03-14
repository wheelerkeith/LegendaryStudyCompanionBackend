package com.revature;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.SubjectDao;
import com.revature.daos.SubjectDaoImpl;
import com.revature.models.Blacklist;
import com.revature.models.BlacklistCompositeKey;
import com.revature.models.Resource;
import com.revature.models.StudySet;
import com.revature.models.Subject;
import com.revature.models.User;
import com.revature.services.BlacklistService;
import com.revature.services.ResourceService;
import com.revature.services.SubjectService;
import com.revature.services.UserLikedResourceService;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

public class Driver {
	
	private static UserService us = new UserService();
	private static SubjectService ss = new SubjectService();
	private static ResourceService rs = new ResourceService();
	private static UserLikedResourceService ulrs = new UserLikedResourceService();
	private static BlacklistService bls = new BlacklistService();
	

	public static void main(String[] args) {

		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		Subject subject = (Subject) ac.getBean("subject");
		System.out.println(subject);
		
		StudySet studySet = (StudySet) ac.getBean("studySet");
		System.out.println(studySet);
		
		Resource resource = (Resource) ac.getBean("resource");
		System.out.println(resource);
		
		User user = (User) ac.getBean("user");
		System.out.println(user);
		
		Blacklist blacklist = (Blacklist) ac.getBean("blacklist");
		System.out.println(blacklist);
		
		
//		Session s = HibernateUtil.getSession();
//		
//		s.close();
//		
//		
//		
		System.out.println();
		User u = new User("jortiz", "pass", "jortiz@mail.com", "Josue Ortiz", 1);
		us.addUser(u);
		User u2 = new User("oduarte", "2ez", "oduarte@mail.com", "Oscar Duarte", 1);
		us.addUser(u2);
		User u3 = new User("lolap", "1234", "lolap@mail.com", "Lola Palmer", 0);
		us.addUser(u3);
		System.out.println();
		
		User u4 = new User();
		u4.setUserName("lolap");
		u4.setPassword("1234");
		System.out.println("VALIDATING: " + us.validateUser(u4));
		System.out.println();
		
		System.out.println(("GET BY ID: " + us.getUserById(2)));
		System.out.println();
		
		System.out.println("LIST ALL USERS: " + us.getAllUsers());
		System.out.println();
		
		u.setFullName("Blueford Fortinbras");
		System.out.println("UPDATE USER: did it update: " + us.updateUser(u));
		System.out.println(("GET BY ID: " + us.getUserById(1)));
		System.out.println();
		
		System.out.println("REMOVE USER: did it delete: " + us.removeUser(u3));
		System.out.println("LIST ALL USERS: " + us.getAllUsers());
		System.out.println();
//		
//
//		
		Subject s = new Subject(".Net");		
		System.out.println("ADD SUBJECT .NET: " + ss.addSubject(s));
		Subject s0 = new Subject("Math");
		System.out.println("ADD SUBJECT MATH: " + ss.addSubject(s0));
		Subject s1 = new Subject("History");
		System.out.println("ADD SUBJECT HISTORY: " + ss.addSubject(s1));
		Subject s2 = new Subject("Java");
		System.out.println("ADD SUBJECT JAVA: " + ss.addSubject(s2));
		System.out.println();
		
		System.out.println("GET BY ID 4: " + ss.getSubjectById(4));
		System.out.println();
		
		System.out.println("GET ALL SUBJECTS: " + ss.getAllSubjects());
		System.out.println();
		
		s1.setName("C++");
		System.out.println("UPDATE HISTORY TO C++: " + ss.updateSubject(s1));
		System.out.println(ss.getAllSubjects());
		System.out.println();
		
		System.out.println("REMOVE MATH SUBJECT: " + ss.removeSubject(s0));
		System.out.println(ss.getAllSubjects());
		System.out.println();
//		
//		Resource r = new Resource("https://dotnet.microsoft.com/learn/dotnet/what-is-dotnet", s);
//		System.out.println("ADD RESOURCE FOR .NET: " + rs.addResource(r));
//		Resource r0 = new Resource("https://www.wolframalpha.com", s1);
//		System.out.println("ADD RESOURCE FOR MATH: " + rs.addResource(r0));
//		Resource r1 = new Resource("https://en.wikipedia.org/wiki/Football_War", s1);
//		System.out.println("ADD RESOURCE FOR HISTORY: " + rs.addResource(r1));
//		Resource r2 = new Resource("https://www.oracle.com/technetwork/java/javase/jdbc/index.html", s2);
//		System.out.println("ADD RESOURCE FOR JAVA: " + rs.addResource(r2));
//		Resource r3 = new Resource("https://junit.org/junit5/", s2);
//		System.out.println("ADD RESOURCE FOR JAVA: " + rs.addResource(r3));
//		Resource r4 = new Resource("https://www.w3schools.com/asp/webpages_intro.asp", s);
//		System.out.println("ADD RESOURCE FOR .NET: " + rs.addResource(r4));
//		Resource r5 = new Resource("https://www.w3schools.com/java/", s2);
//		System.out.println("ADD RESOURCE FOR JAVA: " + rs.addResource(r5));
//		System.out.println();
//		
//		System.out.println("GET BY ID 5: " + rs.getResourceById(5));
//		System.out.println();
//		
//		System.out.println("GET ALL RESOURCES: " + rs.getAllResources());
//		System.out.println();
//		
//		
//		System.out.println("UPDATE HISTORY Resource rating to 3: " + rs.updateResource(r1));
//		System.out.println(rs.getAllResources());
//		System.out.println();
//		
//		System.out.println("REMOVE Resource ID 6: " + rs.removeResource(r4));
//		System.out.println(rs.getAllResources());
//		
//		us.addResourceToList(u3, r5);
//		us.addResourceToList(u3, r4);
//		us.addResourceToList(u2, r4);
//		us.addResourceToList(u3, r3);
//		us.addResourceToList(u, r3);
//		us.addResourceToList(u2, r3);
//		us.addResourceToList(u3, r2);
//		us.addResourceToList(u3, r1);
//		
//		System.out.println(us.getUserById(3));
//		System.out.println();
//		System.out.println("GET BY URL: " + rs.getResourceByUrl("https://junit.org/junit5/"));
//		
//		System.out.println("GET RESOURCE RATING: " + ulrs.getResourceRating(5));
//		
//		System.out.println("DOES THE USER HAVE THE RESOURCE: " + us.findResourceInLikedList(u2, r4));
//		
//		bls.addToBlacklist(1, 1);
//		bls.addToBlacklist(2, 3);
//		bls.addToBlacklist(3, 3);
//		bls.addToBlacklist(4, 4);
//		bls.addToBlacklist(5, 4);
//		bls.addToBlacklist(6, 1);
//		bls.addToBlacklist(7, 4);
//		
//		System.out.println("HERE IS YOUR BLACKLIST: " + bls.getBlacklistByIds(6, 1));
//		
//		System.out.println("ALL THE BLACKLISTS: " + bls.getAllFromBlacklist());
//		
//		bls.approveBlacklistEntry(new Blacklist(new BlacklistCompositeKey(4,4)));
//		bls.denyBlacklistEntry(new Blacklist(new BlacklistCompositeKey(2,3)));
//		
//		
//		Blacklist bll = new Blacklist();
//		bll.setCompKey(new BlacklistCompositeKey(5, 4));
//		
//		bls.removeBlacklistEntry(bll);
		
	}

}
