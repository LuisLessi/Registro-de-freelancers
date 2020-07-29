package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import services.Department;
import services.HourContract;
import services.Worker;
import services.enums.WorkerLevel;

public class Main {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String dpName = sc.nextLine();
		Department department = new Department(dpName);
		
		System.out.println("Enter worker data:");
		
		System.out.print("Name:");
		String name = sc.nextLine();
		System.out.print("Level:");
		String levelSc = sc.nextLine();
		System.out.print("Base Salary:");
		Double baseSalary = sc.nextDouble();
		
		WorkerLevel level = WorkerLevel.valueOf(levelSc);
		Worker worker = new Worker(name, level, baseSalary, department);
		
		System.out.print("How many contracts to this worker?: ");
		int contracts = sc.nextInt();
		
		for(int i = 0; i < contracts; i++) {
			i+=1;
			System.out.println("Enter contract #"+i+" data: ");
			i-=1;
			
			System.out.print("Date (DD/MM/YYYY): ");
			String date0 = sc.next();
			df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = df.parse(date0);

			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract hourContract = new HourContract(date, valuePerHour, hours);
			worker.addContract(hourContract);
		}

		System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
		String Month_Year = sc.next();
		int month = Integer.parseInt(Month_Year.substring(0,2));
		int year = Integer.parseInt(Month_Year.substring(3));
		
		System.out.println("Name: "+ worker.getName());
		System.out.println("Department: "+ worker.getDepartment().getName());
		System.out.println("Income for "+ Month_Year+": "+ String.format("%.2f", worker.income(year, month)));
		sc.close();
	}

}
