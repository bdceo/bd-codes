package com.google.protobuf.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.protobuf.test.AddressBookProts.AddressBook;
import com.google.protobuf.test.AddressBookProts.Person;

public class AddPerson {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("AddPerson ADDRESS_BOOK_FILE");
			System.exit(-1);
		}

		AddressBook.Builder addressBook = AddressBook.newBuilder();

		try {
			addressBook.mergeFrom(new FileInputStream(args[0]));
		} catch (Exception e) {
			System.out.println(args[0]
					+ ": File not found.  Creating a new file.");
		}

		// Add an address.
		addressBook.addPerson(PromptForAddress(new BufferedReader(
				new InputStreamReader(System.in)), System.out));

		FileOutputStream output = new FileOutputStream(args[0]);
		// Write the new address book back to disk.
		addressBook.build().writeTo(output);
		output.close();

		// show the address book
		byte[] data = addressBook.build().toByteArray();
		AddressBook showAddress = AddressBook.parseFrom(data);
		ShowPerson.Print(showAddress);

	}

	static Person PromptForAddress(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		Person.Builder person = Person.newBuilder();

		stdout.print("Enter person ID: ");
		person.setId(Integer.valueOf(stdin.readLine()));

		stdout.print("Enter name:");
		person.setName(stdin.readLine());

		stdout.print("Enter email address (blank for none): ");
		String email = stdin.readLine();
		if (email.length() > 0) {
			person.setEmail(email);
		}

		while (true) {
			stdout.print("Enter a phone number (or leave blank to finish):");
			String number = stdin.readLine();
			if (number.length() == 0) {
				stdout.println("");
				break;
			}

			Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber
					.newBuilder();
			phoneNumber.setNumber(number);

			stdout.print("Is this a mobile, home, or work phone?");
			String type = stdin.readLine();
			if (type.equals("mobile")) {
				phoneNumber.setType(Person.PhoneType.MOBILE);
			} else if (type.equals("home")) {
				phoneNumber.setType(Person.PhoneType.HOME);
			} else if (type.equals("work")) {
				phoneNumber.setType(Person.PhoneType.WORK);
			} else {
				stdout.println("Unknown phone type.  Using default.");
			}

			person.addPhone(phoneNumber);
		}

		return person.build();
	}

}
