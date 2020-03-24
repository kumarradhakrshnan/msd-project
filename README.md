1) The Hadoop ecosystem needs to be installed first. Either we can go for Hortonworks Sandbox or install the ecosystem manually in your machine.
In my case, I would be manually setting up the hadoop ecosystem as the following.

	a) Install Hadoop any version greater than 2.9 and follow the steps associated with its setup. The below resource is really helpful to setup one.
	
	https://kontext.tech/column/hadoop/246/install-hadoop-300-in-windows-single-node

	b) Install Hive any version greater than 2.9 and follow the steps associated with its setup. The below resource is really helpful to setup one. I used derby as my database as its inbuilt.
	
	https://kontext.tech/column/hadoop/291/apache-hive-300-installation-on-windows-10-step-by-step-guide

	c) Install cygwin to run your linux commands while working.	

	d) Install MySql/Oracle to act as your database client optionally.
	
	e) Install Eclipse or IntelliJ as your IDE.

	f) Install Spark version 2.4 and above.	

	g) Make sure you have JAVA_HOME, HIVE_HOME, HADOOP_HOME and SPARK_HOME as environmental variables and path variable well specified as /bin folder.

	h) Once you hadoop is setup, run the command 'start-all' to start the node manager, resource manager, name node and data node.	

2) In order to download the script from the specific URL and  put it into HDFS, we use the below CURL command which simplies the task.

	curl -sS https://chronicdata.cdc.gov/views/735e-byxc/rows.csv?accessType=DOWNLOAD | hdfs dfs -put - /msd/data.csv

3) The next step would be to write a Java-Spark program to calculate the below 

	- Average of each Question’s "Data_Value" by year for all age groups
	- Average of each Question’s "Data_Value" by year for female only

	For this, we will be following a two step process.
	
	a) Since we are using SpringBoot for interaction with Hive Database that was created above, we create a project named msd-spring and put our Spring related Java files for our interaction with Hive Database
	The SpringHiveMainRunner.java is the entry for the SpringBoot application and needs to be run in order to access Hive Database. For more information, refer to the msd-spring package in GitHub. A jar file has been provided which can be run to start SpringBoot application using following command.

	    java -jar target/msd-spring-0.0.1-SNAPSHOT.jar

	2) Our calculations demand Spark jobs so for this, we created a package named msd-spark package contains the relevant spark related files. 
	The MSDDriver.java program needs to be run to calculate the above and save onto Hive through Springboot. For more information, refer to the msd-spark package in GitHub. A jar file has been provided which can be run to start a Spark application using following command.

	./spark-submit --master yarn  --name core-engine-c2  --class com.hive.driver.MSDDriver  --deploy-mode cluster  --num-executors 2 	--executor-memory 1G --executor-cores 2  --driver-memory 1G --driver-cores 1 /target/msd-spark-0.0.1-SNAPSHOT.jar

4) For the front end, ReactJS has been used. For setting up ReactJs in your machine, steps as the following needs to be executed.

	npx create-react-app msd-reactjs
	
	cd my-app
	
	npm add axios
	
	npm add react-router-dom
	
	npm start

	Once done, install the Advanced Rest Client plugin for Chrome which will aid in calling REST Services.
	Finally, when you are done setting up the ReactJS component and putting the relevant changes as given in the GitHub URL. Run the following url to see the result.

	http://localhost:3000/
