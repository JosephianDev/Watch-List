package marrone.giuseppe.watchlist.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Date implements Serializable {
	private int day;
	private int month;
	private int year;

	//day
	public void setDay(int day) {
		this.day = day;
	}
	public int getDay() {
		return day;
	}
	//month
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth() {
		return month;
	}
	//year
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	
	public static Date dataStringToDate(String dataString) {
		Date tmp = new Date();
		String[] arrSplit = dataString.split(" ");
		
		tmp.setDay(Integer.parseInt(arrSplit[0]));
		tmp.setYear(Integer.parseInt(arrSplit[2]));
		switch(arrSplit[1]) {
		case "January":case "Jan":						arrSplit[1]="1";break;
		case "February":case "Feb":						arrSplit[1]="2";break;
		case "March":case "Mar":						arrSplit[1]="3";break;
		case "April":case "Apr":						arrSplit[1]="4";break;
		case "May":										arrSplit[1]="5";break;
		case "June":case "Jun":							arrSplit[1]="6";break;
		case "July":case "Jul":							arrSplit[1]="7";break;
		case "August":case "Aug":						arrSplit[1]="8";break;
		case "September":case "Sept":case "Sep":		arrSplit[1]="9";break;
		case "October":case "Oct":						arrSplit[1]="10";break;
		case "November":case "Nov":						arrSplit[1]="11";break;
		case "December":case "Dec":						arrSplit[1]="12";break;
		}
		tmp.setMonth(Integer.parseInt(arrSplit[1]));
		
		return tmp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Date date = (Date) o;
		return day == date.day && month == date.month && year == date.year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	@NonNull
	@Override
	public String toString() {
		return day+"/"+month+"/"+year;
	}
	
	//constructors
	public Date() {
		
	}

	public Date(String date){
		Date a = dataStringToDate(date);

		setDay(a.getDay());
		setMonth(a.getMonth());
		setYear(a.getYear());
	}

	public Date(Long dateLong) {
		java.util.Date date = new java.util.Date(dateLong);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		setDay(calendar.get(Calendar.DAY_OF_MONTH));
		setMonth(calendar.get(Calendar.MONTH));
		setYear(calendar.get(Calendar.YEAR));
	}

	public Date(Date date) {
		this.day = date.getDay();
		this.month = date.getMonth();
		this.year = date.getYear();
	}
	
	public Date(int day,int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	public Long getTime() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.MONTH, month);
		c1.set(Calendar.DATE, day);
		c1.set(Calendar.YEAR, year);

		return c1.getTime().getTime();
	}
}
