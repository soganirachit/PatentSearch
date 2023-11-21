package com.patent;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestJavaSpark {

	public static void main(String[] args) {

		System.setProperty("hadoop.home.dir", "C:\\spark-3.5.0-bin-hadoop3\\");

		SparkSession spark = SparkSession.builder().master("local").appName("Test-spark").getOrCreate();

		Dataset<Row> ds = spark.read().format("csv").option("header", true)
				.load("C:\\Users\\rachit sogani\\Downloads\\application_details_granted_2023.csv");

		Dataset<Row> dsChem = ds.filter(ds.col("FIELD_OF_INVENTION").equalTo("CHEMISTRY"));
		Dataset<Row> dsMech = ds.filter(ds.col("FIELD_OF_INVENTION").equalTo("MECHANICAL"));
		Dataset<Row> dsJoined = dsChem
				.join(dsMech, dsChem.col("IPO_LOCATION").equalTo(dsMech.col("IPO_LOCATION")), "inner")
				.drop(dsChem.col("IPO_LOCATION"));

		System.out.println("dsChem count is : " + dsChem.count());
		dsChem.show();
		System.out.println(
				".................................................................................................");
		System.out.println("dsMech count is : " + dsMech.count());
		dsMech.show();
		System.out.println(
				".................................................................................................");
		System.out.println("dsJoined count is : " + dsJoined.count());
		dsJoined.show(20);
	}

}
