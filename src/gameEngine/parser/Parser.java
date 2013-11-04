package gameEngine.parser;

import java.util.Scanner;

import gameEngine.parser.JSONLibrary.*;

public class Parser {
	private Scanner scanner;
	private JSONObject jsonObject;
	public Parser(Scanner scanner) {
		this.scanner = scanner;
		jsonObject = new JSONObject(constructJSONString());
	}
	
	public String constructJSONString() {
		String returnString = "";
		while(scanner.hasNextLine()) {
			returnString = returnString + scanner.nextLine();
		}
		return returnString;
	}
	
	public String getValue(String key) {
		return (String) jsonObject.get(key);
	}
}
