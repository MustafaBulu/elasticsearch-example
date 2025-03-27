# Elasticsearch Example

## Project Description
This Spring Boot application provides a REST API for product management with advanced search capabilities using Elasticsearch. It offers full-text and fuzzy search features for efficient product querying.

## Features
- Product management with Elasticsearch integration
- Full-text and fuzzy search support
- Product filtering by price range
- Bulk product addition
- RESTful API endpoints

## Prerequisites
- Java 17+
- Spring Boot 3.x
- Elasticsearch 7.x or 8.x
- Maven

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/MustafaBulu/elasticsearch-example.git
cd elasticsearch-example
```

### 2. Configure Elasticsearch
Ensure Elasticsearch is running on your local machine or update the connection settings in `application.properties`

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

## API Endpoints

### Product Operations
- `POST /api/products`: Create a single product
- `POST /api/products/bulk`: Add multiple products
- `GET /api/products/{id}`: Retrieve product by ID
- `GET /api/products`: List all products
- `DELETE /api/products/{id}`: Delete a product

### Search Endpoints
- `GET /api/products/search/title?keyword=...`: Search in title and description
- `GET /api/products/search/price?minPrice=...&maxPrice=...`: Search by price range

## Search Capabilities
- Multi-field search across title and description
- Fuzzy matching with automatic error tolerance
- Price range filtering

## Example Requests

### Adding a Product
```json
{
  "title": "Smart Phone",
  "description": "Next-generation smartphone",
  "price": 599.99
}
```

### Search Examples
- Fuzzy Search: `GET /api/products/search/title?keyword=phone`
- Price Range: `GET /api/products/search/price?minPrice=100&maxPrice=1000`

## Configuration
Customize Elasticsearch connection in `application.properties`:
```properties
spring.elasticsearch.uris=localhost:9200
```

## Technology Stack
- Spring Boot
- Spring Data Elasticsearch
- Lombok
- MapStruct

## Project Structure
- `ProductController`: REST API endpoints
- `ProductService`: Business logic
- `ProductRepository`: Elasticsearch repository
- `Product`: Domain model
- `ProductDto`: Data transfer object

## Potential Improvements
- Add pagination to search results
- Implement more complex search queries
- Enhanced error handling
- Comprehensive logging

