# Devoxx 2018

## Quickstart your Event Driven Architecture

> By: Ben Wilcock (Pivotal) & Steven van Beelen (AxonIQ)

## Abstract

Axon Trader is a new open-source reference architecture that demonstrates how to deliver "evolutionary" microservice applications to production in minutes. 


## PCF Commands

Pushing an application (with a manifest.yml)

`cf push`

Creating a MySQL Database (on PWS)

`cf create-service cleardb spark mysql`

Binding a database

` cf bind-service wallet mysql`

## PCF Manifest

```yaml
applications:
- name: wallet
  path: target/wallet-0.0.1-SNAPSHOT.jar
  instances: 1
  routes:
  - route: wallet.cfapps.io
```
