# Devoxx 2018

## Quickstart your Event Driven Architecture

> By: Ben Wilcock (Pivotal) & Steven van Beelen (AxonIQ)

## Abstract

Axon Trader is a new open-source reference architecture that demonstrates how to deliver "evolutionary" microservice applications to production in minutes.

This repository provides a demo Wallet application following a similar route as the Axon Trader application,
 used during a live coding presentation at Devoxx Antwerp 2018.
 
As such, this sample app utilizes both Spring Boot and Axon Framework to provide a small use case of how CQRS,
 Event Sourcing and DDD can be incorporated. 

## Demo Commands

Pushing an application (with a manifest.yml)

`cf push`

Check the application...

`curl -X GET wallet.cfapps.io/hello`

Initialise the application...

`curl -X GET wallet.cfapps.io/command`

Creating a MySQL Database (on PWS)

`cf create-service cleardb spark mysql`

Binding a database

` cf bind-service wallet mysql`

Initialise the application...

`curl -X GET wallet.cfapps.io/command`

Query for Wallets...

`curl -X GET wallet.cfapps.io/query/wallets`

## Wallet Manifest

```yaml
applications:
- name: wallet
  path: target/wallet-0.0.1-SNAPSHOT.jar
  instances: 1
  routes:
  - route: wallet.cfapps.io
```
