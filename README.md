# g-pubsub-subscriber
# Google PubSub Subscriber

This is the Google PubSub Subscriber service, built using Kotlin, Spring Boot, and Java 11. Its primary function is to connect to Google Cloud Pub/Sub topics and start receiving messages as soon as they are emitted to the topics.

## Features

1. Connects to Google Cloud Pub/Sub topics.
2. Receives messages as they are published in real-time.

## Requirements

- JDK 11
- Kotlin
- Spring Boot
- Google Cloud Pub/Sub

## Getting Started

### Setting Up

1. `git clone https://github.com/<yourusername>/google-pubsub-subscriber.git`
2. `cd google-pubsub-subscriber`
3. Open the project in your favorite IDE.

### Configuring Google Cloud Credentials

To authenticate your application with Google Cloud, provide your Google Cloud service account key.

1. Download your `json` key file from Google Cloud Console under IAM & Admin > Service Accounts.

2. Place the key file in the `src/main/resources` directory of your project.

3. Update the `application.properties` file with the following:

```yaml
spring.cloud.gcp.project-id=yourGoogleCloudProjectId
spring.cloud.gcp.credentials.location=classpath:yourGoogleCloudCredentials.json
```
Example:

```yaml
spring.cloud.gcp.project-id=codewithsohrab
spring.cloud.gcp.credentials.location=classpath:codewithsohrab-c8c320d9c539.json
```

Replace `yourGoogleCloudProjectId` and `yourGoogleCloudCredentials.json` with your Google Cloud Project ID and your Google Cloud Credentials key file respectively.

## Building and Running

To compile the source code:

```shell
./mvnw clean package
```
