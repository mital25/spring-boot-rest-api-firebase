package com.mdcsolutions.firebaseAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@RestController
public class DemoController {
	@Autowired
	FirebaseInitializer db;

	@GetMapping("/getallpersons")
	public List<Persons> getallpersons() throws InterruptedException, ExecutionException {
		List<Persons> personsList = new ArrayList<Persons>();
		CollectionReference persons = db.getFirebase().collection("persons");
		ApiFuture<QuerySnapshot> querySnapshot = persons.get();
		for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
			Persons person = doc.toObject(Persons.class);
			personsList.add(person);
		}
		return personsList;
	}

	@GetMapping("/getmalepersons")
	public List<Persons> getmalepersons() throws InterruptedException, ExecutionException {
		String male = "Male";
		List<Persons> personsList = new ArrayList<Persons>();
		CollectionReference persons = db.getFirebase().collection("persons");
		ApiFuture<QuerySnapshot> querySnapshot = persons.get();
		for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
			Persons person = doc.toObject(Persons.class);
			if (male.equalsIgnoreCase(person.getGender()))
				personsList.add(person);
		}
		return personsList;
	}

	@GetMapping("/getfemalepersons")
	public List<Persons> getfemalepersons() throws InterruptedException, ExecutionException {
		String female = "Female";
		List<Persons> personsList = new ArrayList<Persons>();
		CollectionReference persons = db.getFirebase().collection("persons");
		ApiFuture<QuerySnapshot> querySnapshot = persons.get();
		for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
			Persons person = doc.toObject(Persons.class);
			if (female.equalsIgnoreCase(person.getGender()))
				personsList.add(person);
		}
		return personsList;
	}

	@GetMapping("/login/{uid}/{upwd}")
	public Map<String, String> getperson(@PathVariable String uid, @PathVariable String upwd)
			throws InterruptedException, ExecutionException {

		boolean foundflag = false;
		List<Persons> personsList = new ArrayList<Persons>();
		CollectionReference persons = db.getFirebase().collection("persons");
		ApiFuture<QuerySnapshot> querySnapshot = persons.get();
		for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
			Persons person = doc.toObject(Persons.class);
			personsList.add(person);
		}

		for (Persons p1 : personsList) {
			if (uid.equalsIgnoreCase(p1.getUserid()) & upwd.equals(p1.getPassword()))
				foundflag = true;
		}

		HashMap<String, String> map = new HashMap<>();

		if (foundflag) {
			map.put("status", "1");
			map.put("message", "login success");
			// System.out.println(map);
			return map;
		} else {
			map.put("status", "2");
			map.put("message", "Incorrect Credentials");
			// System.out.println(map);
			return map;
		}
	}

	@GetMapping("/getpersondetails/{uid}/{upwd}")
	public Persons getpersondetails(@PathVariable String uid, @PathVariable String upwd)
			throws InterruptedException, ExecutionException {

		boolean foundflag = false;
		Persons pdetails = new Persons();
		List<Persons> personsList = new ArrayList<Persons>();
		CollectionReference persons = db.getFirebase().collection("persons");
		ApiFuture<QuerySnapshot> querySnapshot = persons.get();
		for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
			Persons person = doc.toObject(Persons.class);
			personsList.add(person);
		}

		for (Persons p1 : personsList) {
			if (uid.equalsIgnoreCase(p1.getUserid()) & upwd.equals(p1.getPassword())) {
				foundflag = true;
				pdetails = p1;
			}
		}

		if (foundflag) {
			return pdetails;
		} else {
			return null;
		}
	}

	@PostMapping("/signup")
	public String signup(@RequestBody Persons person) {
		CollectionReference personCR = db.getFirebase().collection("persons");
		personCR.document(String.valueOf(person.getUserid())).set(person);
		return person.getUserid();
	}
}
