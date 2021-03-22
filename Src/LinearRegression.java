
import java.io.*;  
import java.util.*;
import java.util.Scanner;  
import java.util.List;
public class LinearRegression {
//needed data
private static List<Float> areas = new ArrayList<Float>();
private static List<Float> rooms = new ArrayList<Float>();
private static List<Float> bathrooms = new ArrayList<Float>();
private static List<Float> parkingSpaces = new ArrayList<Float>();
private static List<Float> floors = new ArrayList<Float>();
private static List<Float> totalPrices = new ArrayList<Float>();

public static void main(String[] args) throws Exception{
ScrapeData("C:\\Users\\dtech\\Desktop\\My First ML Program\\Datasets\\houses_to_rent_v2.csv");
Scanner scan = new Scanner(System.in);
System.out.println("Enter The Area : ");
float area = scan.nextFloat();
System.out.println("Enter The Rooms Number :");
float roomsNum = scan.nextFloat();
System.out.println("Enter Bathrooms Number :");
float bathroomsNum = scan.nextFloat();
System.out.println("Enter The Number Of Parking Spaces :");
float parkingSpacesNum = scan.nextFloat();
System.out.println("Enter The Floor");
float floor = scan.nextFloat();
scan.close();
float priceByArea =  getSlope(areas,totalPrices) * area + getDistanceToCenter(areas,totalPrices,getSlope(areas,totalPrices));
System.out.println("price by area : " + priceByArea);
float priceByRoomsNum = getSlope(rooms,totalPrices) * roomsNum +getDistanceToCenter(rooms,totalPrices,getSlope(rooms,totalPrices));
System.out.println("price by rooms number : " + priceByRoomsNum);
float priceByBathroomsNum = getSlope(bathrooms,totalPrices) * bathroomsNum + getDistanceToCenter(bathrooms,totalPrices,getSlope(bathrooms,totalPrices));
System.out.println("price by bathrooms number : " + priceByBathroomsNum);
float priceByParkingSpacesNum = getSlope(parkingSpaces,totalPrices) * parkingSpacesNum + getDistanceToCenter(parkingSpaces,totalPrices,getSlope(parkingSpaces,totalPrices));
System.out.println("price by parking spaces : " + priceByParkingSpacesNum);
float priceByFloor = getSlope(floors,totalPrices) * floor + getDistanceToCenter(floors,totalPrices,getSlope(floors,totalPrices));
System.out.println("price by floor : " + priceByFloor);
float myTotalPrice = (priceByParkingSpacesNum + priceByRoomsNum + priceByBathroomsNum + priceByArea + priceByFloor) / 5;
System.out.println("Total Rent Price Is : " + myTotalPrice);
}

private static void ScrapeData(String filePath) throws Exception{
 //reading the data from the csv file
 File dataFile = new File(filePath);
 Scanner scan = new Scanner(dataFile);
 int i = 1;
 scan.useDelimiter(",|\\n");
 while(scan.hasNext()){
 	String scanData = scan.next();
 	//trying to put the data in the right place
    switch(i){
    case 2 :
    if(scanData.equals("-"))
    areas.add(0f);
    else	
    areas.add(Float.parseFloat(scanData));
    break;

    case 3 :
    if(scanData.equals("-"))
    rooms.add(0f);
    else	
    rooms.add(Float.parseFloat(scanData));
    break;

    case 4 : 
    if(scanData.equals("-"))
    bathrooms.add(0f);
    else	
    bathrooms.add(Float.parseFloat(scanData));
    break;

    case 5 :
    if(scanData.equals("-"))
    parkingSpaces.add(0f);
    else	
    parkingSpaces.add(Float.parseFloat(scanData));
    break;

    case 6 :
    if(scanData.equals("-"))
    floors.add(0f);
    else	
    floors.add(Float.parseFloat(scanData));
    break;

    case 13 :
    if(scanData.equals("-"))
    totalPrices.add(0f);
    else	
    totalPrices.add(Float.parseFloat(scanData));
    break;

    }
    if(i == 13)
      i = 1;
    else 
      i++;
   }
 scan.close();
}
  private static float getSlope(List<Float> data,List<Float> prices){
  List<Float> dataByPrices = new ArrayList<Float>();
  for(int i = 0;i < prices.size(); i++){
  dataByPrices.add(data.get(i) * prices.get(i));
  }

  List<Float> dataSquared = new ArrayList<Float>();
  for(int i = 0;i < data.size(); i++){
  dataSquared.add(data.get(i) * data.get(i));
  }
  float slope = ((data.size() * sum(dataByPrices)) - (sum(data) * sum(prices))) / ((data.size() * sum(dataSquared)) - (sum(data) * sum(data)));
  return slope;
 }

 private static float sum(List<Float> values){
    float sumResult = 0;
    for(float value : values)
    sumResult += value;

    return sumResult;
   }

  private static float getDistanceToCenter(List<Float> data,List<Float> prices, float slope){
   return (sum(prices) - (slope * sum(data))) / data.size();
  }
}