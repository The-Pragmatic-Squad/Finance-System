# Finance System [Core Module]

The Finance System is a core module of the mini Fwary system. It is responsible for managing debit and credit transactions across accounts. This README file provides an overview of the Finance System module, its setup, and usage instructions.

## Table of Contents
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository or download the source code for the Finance System module.
2. Ensure that you have Java Development Kit (JDK) version 17 or higher installed.
3. Set up your development environment (e.g., IDE such as IntelliJ or Eclipse).
4. Ensure that you have the right configs for the project

## Configuration

The Finance System module requires a PostgreSQL database for storing account and transaction data, RabbitMQ to Listen for Transactions from the Gateway.
We are using Docker to create a two containers for PostgreSQL and RabbitMQ but if want to install them manualy follow the following steps:

### Configure the Database

1. Install PostgreSQL if you haven't already.
2. Create a new database for the Finance System module.
3. Update the database configuration in the `application.properties` file located in the module's resources folder.
4. Provide the appropriate values for the following properties:
   - `spring.datasource.url`: JDBC URL for connecting to the database.
   - `spring.datasource.username`: Username for the database connection.
   - `spring.datasource.password`: Password for the database connection.

### configure the RabbitMQ 

1. Follow the RabbitMQ recommended installation steps according to your OS https://www.rabbitmq.com/download.html
2. Update the RabbitMQ configurations in the `application.properties` file located in the module's resources folder.
3. Provide the appropriate values for the following properties:
   - `spring.rabbitmq.port`, `spring.rabbitmq.host`, `spring.rabbitmq.username`, `spring.rabbitmq.password`.

## Running the Application

To run the Finance System module, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the directory containing the Finance System module.
3. Run the following command to build the project:
   ```bash
   mvn clean install
   ```
4. Once the build is successful, run the following command to start the application:
   ```bash
   mvn spring-boot:run
   ```
5. The Finance System module will start, and you will see log messages indicating the successful startup.

PS: Make sure that RabbitMQ and PostgreSQL are running and the `application.properties` file is configured with right configs
## API Documentation

The Finance System module exposes RESTful APIs for performing account and transaction operations. This documentation provides details about the available endpoints, request and response formats, and example usage. You can also access this documentation through the [Postman Collection](https://planetary-water-707257.postman.co/workspace/Fawry~4fb003f9-33ab-49b5-b5ef-f94cf7d20646/collection/8654587-a9715750-c543-449c-a8fe-aed02afe5390?action=share&creator=8654587) associated with the Finance System module.

 
### Base URL
The base URL for all API endpoints is: `http://localhost:9000`
 

## Contributing

Contributions to the Finance System module are welcome. If you find any issues or have suggestions for improvement, please feel free to open an issue or submit a pull request. Ensure that you follow the existing code style and conventions.

## License

The Finance System module is open-source and distributed under the [MIT License](LICENSE). You are free to use, modify, and distribute the codebase as per the terms of the license.

---

Thank you for your interest in the Finance System module. If you have any questions or need further assistance, please don't hesitate to contact us.
