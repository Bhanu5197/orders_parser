package com.unlimint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class Application {

	public static void main(String[] args) throws IOException, CsvException, ParseException {
		List<Output> outputs = new ArrayList<Output>();
		int outputLines = 0;

		File f = new File(args[0]);
		List<Order> csvOrders = new CsvToBeanBuilder(new FileReader(f.getAbsolutePath())).withType(Order.class).build()
				.parse();

		int csvLines = 0;
		for (Order order : csvOrders) {
			Output output = new Output();
			output.setId(++outputLines);
			output.setAmount(order.getAmount());
			output.setComment(order.getComment());
			output.setFileName("orders.csv");
			output.setLine(++csvLines);
			output.setOrderId(order.getOrderId());
			output.setResult("OK");
			outputs.add(output);
		}

		ArrayList<JSONObject> json = new ArrayList<JSONObject>();
		JSONObject obj;

		File file = new File(args[1]);
		FileReader fileReader = new FileReader(file.getAbsolutePath());
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		int jsonLines = 0;
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			json.add(obj);
			Output output = new Output();
			output.setId(++outputLines);
			output.setAmount((Double) obj.get("amount"));
			output.setComment((String) obj.get("comment"));
			output.setFileName("orders.json");
			output.setLine(++jsonLines);
			output.setOrderId((Long) obj.get("orderId"));
			output.setResult("OK");
			outputs.add(output);
		}
		bufferedReader.close();

		outputs.forEach(o -> System.out.println(new Gson().toJson(o)));
	}

}
