**Design Question**
    
Design A Google Analytic like Backend System. We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

**Requirements**
    
 Handle large write volume: Billions of write events per day.
    Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
    Provide metrics to customers with at most one hour delay.
    Run with minimum downtime.
    Have the ability to reprocess historical data in case of bugs in the processing logic.
    
**-----------------------------Proposed Solution-------------------------------**

**Solution Link** https://miro.com/welcomeonboard/qZI2xl4aw1oTwjYmOT3j28XvqwnE5hylvHwVypH5Q7Sec1PDgkLBmgihYIVidpgw

**PDF Version** GALikeBackendService.pdf (./design-solution/GALikeBackendService.pdf)
    
<H4>Components</H4>
    
  - **User Events**: An event is a metrics that we want to capture and analyze. This could be a user interacting with a feature or just events related to how app is doing, like close opened or crashed.
  - **API Gateway**: entry point for events flowing into the system as a result of user interaction with a product or features.
     Example: How many users in the last hour have seen or interacted with their product.
  - **Load Balancer**: Load balancers route the traffic to appropriate instance of microservice based on either compute power or how busy a given instance is.
  - **EventHandlingService** This is a microservice that is deployed on AWS ECS cluster. 
      We can define minimum and maximum number of instances that we want to scale to based on either the CPU usage or memory usage.
  - **AWS Kinesis Data Firehose** Events are pushed to Kinesis Firehose that is configured to write the RAW events to S3 every 15 mins or when the data size is 100MB.
               We can have multiple AWS Kinesis Firehose for different kinds of events. Each instance of AWS Kinesis Firehose can have multiple shards to handle incoming traffic.
  - **AWS S3** Raw data coming in from Kinesis Fireshose is pushed to S3 based on the criteria define above.
  - **AWS Kinesis Analytics** This aggregates data coming in from Kinesis Firehose based on a custom logic.
               Example: Get the count of all merchant product views in the past 15 mins and group by the merchant id.
  - **AWS Elastic Search** After aggregating data, AWS Kinesis Firehose streams the data into AWS Elastic Search.
  - **InsightsService** Insights Service is another scalable microservice that is deployed on Amazon ECS. This service can query ElasticSearch based on User/Merchant inputs to gain insights into metrics. 
  - **User Analytics Dashboard**: A user interface that provides capability to  merchants/customers to get insights about how their product is doing. InsightsService powers this dashboard. 


<H4>Sequence of Events</H4>
1. A User generates an event.
2. EventHandlingService receives the event and based on the event type it pushes the event with metadata (timestamp, merchant information, event type) to appropriate AWS Kinesis Data Firehose.
3. AWS Kinesis Data Analytics aggregates the data at runtime. 
        Example count the number of a merchant feature has been accessed. This query can be scheduled to run every 15 mins or so. 
   The output of the query streams to another instance of AWS Kinesis Data Firehose.
4. AWS Kinesis Data Firehose streams the aggregated data to AWS Elastic Search.
5. A Merchant logs into the dashboard. 
6. Based on the user inputs and time-range, InsightsService gets data from ElasticSerach via a rest endpoint.
        
        
<H4>Error Handling</H4>
 1. AWS Kinesis Data Firehose stores Raw Events in S3 bucket. This is a source of truth in cases where we have to reprocess the data in case there is some update or issue in the aggregation step.
 2. AWS ElasticSearch gives high performance when marchant is doing queries for gaining insights.
 
 <H4>Alternate Technologies that can also be used</H4>

Apache Kafka can be used instead AWS Kinesis Data Firehose to push event. 
We can have dedicated topics to process a given type of event and can have dedicated Kafka Brokers to handle growing traffic.
Event can be aggregated using either of these technologies:
 1. Spark Job (Qubole or AWS EMR can be used as platform)
 2. Kafka's KSQL can also be used to tranform data.

 

 
