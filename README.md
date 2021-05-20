# bank-sandbox

Toy bank platform for experimenting with [microservices](https://12factor.net/).

No promises for active development but the goal of the project is to serve as best practice full example of [microservices](https://12factor.net/) architecture software platform.

Microservices architecture trades off more complexity to reduce 'time to market' for software development.

Current tech stack: Java 11, [Gradle](https://gradle.org/) and [Docker](https://www.docker.com/) build tools, [Meecrowave](https://openwebbeans.apache.org/meecrowave/index.html) web framework powering [Jakarta RESTful Web Services](https://wikipedia.org/wiki/Jakarta_RESTful_Web_Services) (JAX-RS) and [Java Persistence API](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html) (JPA) to [Apache Derby](https://db.apache.org/derby/) relational database, and [REST-assured](https://github.com/rest-assured/rest-assured) for integration-tests.

## Example usage

### 1. Run relational-database

```bash
$ cd path/to/bank-sandbox
$ docker compose up
```

:information_source: The docker build initialises the [relational database](https://db.apache.org/derby/) with [2 accounts](relational-database/init.sql) to get you started.

### 2. Run platform

For each subproject task `:tranzaction-api:run`, `:tranzaction-api:run` and `:statement-api:run` open new terminal and do:

```bash
$ cd path/to/bank-sandbox
$ ./gradlew subproject-task
```

### 3. Run integration-tests (optional)

In a new terminal:

```bash
$ cd path/to/bank-sandbox
$ ./gradlew :integration-tests:test
```

:information_source: Open bank-sandbox/integration-tests/build/reports/tests/test/index.html to see results.

### 4. Call platform

In a new terminal:

```bash
$ curl --header "Content-Type: application/json" --request POST --data '{"userId":"72bf432c-738f-489a-acc0-8e6a5cf905b9", "initialBalance":"400.27"}' "http://localhost:8080/api/accounts/"
$ curl http://localhost:8080/api/accounts/30e51133-1170-46dd-a19c-2a64a8fa9fe7
$ curl --header "Content-Type: application/json" --request POST --data '{"source":"30e51133-1170-46dd-a19c-2a64a8fa9fe7", "target":"dd7955ea-fcf6-4b38-b728-cafd7161ed5c", "value":"33.0"}' "http://localhost:8070/api/tranzactions/"
$ curl "http://localhost:8080/api/accounts/30e51133-1170-46dd-a19c-2a64a8fa9fe7"
$ curl "http://localhost:8020/api/statements?accountId=30e51133-1170-46dd-a19c-2a64a8fa9fe7"
```

## Future work

* [Continuous integration](https://travis-ci.org/) - [gradle docker integration](https://github.com/avast/gradle-docker-compose-plugin)...
* Tests!
* Page responses (or [reactive programming](https://www.reactivemanifesto.org/)?)
* [Data validation](http://cxf.apache.org/docs/validationfeature.html)
* [Data caching](https://docs.oracle.com/javaee/6/tutorial/doc/gkjjj.html) for performance
* Missing standard endpoints e.g. get all
* Light version of get account (no tranzactions)
* Database to generate `id` UUID
* Move models to their own subproject
* [OpenJPA enhance](https://people.apache.org/~mprudhom/openjpa/site/openjpa-project/manual/ref_guide_pc_enhance.html) classes at compile time for performance - not easy with gradle currently (requires custom work?)
* [Security](https://shiro.apache.org/) - Multi-factor authentication, encryption, ...
* [Identity management](https://syncope.apache.org/)
* [SonarLint](https://www.sonarlint.org/)
* [Developer portal](https://backstage.io/)
* Docker volumes (not trivial with [relational-database/init.sql](relational-database/init.sql))
* More best practices from [The Twelve-Factor App](https://12factor.net/) e.g. configuration, DEV and STAGING environments, ...
* Infrastructure as code - [AWS CloudFormation](https://aws.amazon.com/cloudformation/), [serverless](https://www.serverless.com/), ...
* Javadoc
* ...

but actually [eventually we'll only mostly worry about data and algorithms instead of ops](https://hadean.com/blog/why-distributed-computing-is-about-to-go-fully-serverless/).

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
