package com.google.protobuf.test;

import java.io.FileInputStream;

import com.google.protobuf.test.AddressBookProts.AddressBook;
import com.google.protobuf.test.AddressBookProts.Person;

public class ShowPerson {

	// d:/download/google/addressbook.txt
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
			System.exit(-1);
		}

		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(
				args[0]));

		Print(addressBook);

	}

	static void Print(AddressBook addressBook) {
		System.out.println("----------------------------------------");
		for (Person person : addressBook.getPersonList()) {
			System.out.println("Person ID: " + person.getId());
			System.out.println("  Name:" + person.getName());
			if (person.hasEmail()) {
				System.out.println("  E-mail address: " + person.getEmail());
			}

			for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
				switch (phoneNumber.getType()) {
				case MOBILE:
					System.out.print("  Mobile phone #: ");
					break;
				case HOME:
					System.out.print("  Home phone #: ");
					break;
				case WORK:
					System.out.print("  Work phone #: ");
					break;
				}
				System.out.println(phoneNumber.getNumber());
			}
		}
	}

}
