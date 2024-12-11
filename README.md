# Challenge: Fake Store API and CSV Formula Calculation

This project includes two challenges:

1. **Fake Store API Integration**
2. **CSV Formula Calculation**

---

# 1. Fake Store API Integration

This challenge demonstrates the integration with the [Fake Store API](https://fakestoreapi.com/) by implementing the following functionalities:

## Features

1. **List Product Details by Category**
   - A REST API endpoint that fetches product details (title, price, description, etc.) for a given category.
   - Uses the `GET /products/category/{category}` endpoint from the Fake Store API.

2. **Add a New Product**
   - A REST API endpoint that allows adding a new product entry with relevant properties (title, price, description, etc.).
   - Uses the `POST /products` endpoint from the Fake Store API.

3. **Key Considerations for API Security and Reliability**
   - Authentication and authorization using API keys or tokens.
   - Input validation and data sanitization.
   - Rate limiting to prevent abuse.
   - Error handling and consistent HTTP status codes.
   - Secure data transmission using HTTPS.

---

## Endpoints

### **1. List Products by Category**
#### Endpoint:
```
GET /products/category
```
#### Request Parameters:
| Parameter | Type   | Description                  |
|-----------|--------|------------------------------|
| `category`| String | Category name (e.g., `jewelery`). |

#### Sample Request:
```
GET /products/category?category=jewelery
```

#### Sample Response:
```json
[
  {
    "id": 1,
    "title": "Necklace",
    "price": 150,
    "description": "Beautiful gold necklace.",
    "category": "jewelery",
    "image": "https://fakestoreapi.com/image1.jpg"
  }
]
```

---

### **2. Add a New Product**
#### Endpoint:
```
POST /products
```
#### Request Body:
| Field        | Type   | Description                  |
|--------------|--------|------------------------------|
| `title`      | String | Product title.               |
| `price`      | Number | Product price.               |
| `description`| String | Product description.         |
| `category`   | String | Product category.            |
| `image`      | String | Product image URL.           |

#### Sample Request:
```json
{
  "title": "Gold Ring",
  "price": 200,
  "description": "A shiny gold ring.",
  "category": "jewelery",
  "image": "https://fakestoreapi.com/image2.jpg"
}
```

#### Sample Response:
```json
{
  "id": 21,
  "title": "Gold Ring",
  "price": 200,
  "description": "A shiny gold ring.",
  "category": "jewelery",
  "image": "https://fakestoreapi.com/image2.jpg"
}
```

---

## Key Considerations for API Development

1. **Authentication and Authorization**:
   - Use OAuth2 or JWT tokens to secure endpoints.

2. **Input Validation**:
   - Validate all inputs to prevent SQL injection, XSS, and other attacks.

3. **Rate Limiting**:
   - Implement rate limiting to protect against abuse.

4. **Error Handling**:
   - Provide meaningful error messages and standard HTTP status codes.

5. **Data Encryption**:
   - Ensure all communication is encrypted via HTTPS.

6. **Logging and Monitoring**:
   - Log all API interactions and monitor system health.

---

# 2. Theoretical Challenge: CSV Formula Calculation

This project provides a solution to process a CSV-like input containing values and formulas, computes the results of the formulas, and generates an updated CSV with the computed values.

## Problem Statement

Given a CSV file with data formatted as follows:

|   | A   | B      | C        |
|---|------|--------|----------|
| 1 | 5    | 3      | =5+A1    |
| 2 | 7    | 8      | =A2+B2   |
| 3 | 9    | =4+5   | =C2+B3   |

The program should:
1. Read the CSV input with values and formulas.
2. Evaluate formulas to compute their values.
3. Produce a CSV output with all formulas replaced by their computed results.

### Sample Input
```csv
A1: 5, A2: 7, A3: 9, B1: 3, B2: 8, B3: =4+5, C1: =5+A1, C2: =A2+B2, C3: =C2+B3
```

### Sample Output
```csv
A1: 5, A2: 7, A3: 9, B1: 3, B2: 8, B3: 9, C1: 10, C2: 15, C3: 24
```

---

## Solution Approach

1. **Parsing the Input**:
   - Read the CSV input and store it in a 2D array structure for easy access.
   - Maintain the cell references (e.g., `A1`, `B3`) using their row and column indices.

2. **Evaluating the Formulas**:
   - Identify cells containing formulas (starting with `=`).
   - Parse the formula to extract references (e.g., `A1`, `B2`) and operators.
   - Compute the formula recursively, resolving all dependencies.

3. **Error Handling**:
   - Detect and handle invalid references (e.g., `A10` when the sheet only has rows 1-3).
   - Handle circular dependencies gracefully (e.g., `A1 = B1 + C1` and `C1 = A1 + 5`).
   - Validate mathematical expressions for syntax errors.

4. **Generating the Output**:
   - Replace formulas in the original structure with their computed values.
   - Write the updated data back to a CSV file.

---

## Key Considerations

### 1. Tackling the Challenge
- Use a 2D array to store the spreadsheet for efficient access.
- Implement a recursive function to evaluate formulas with support for basic arithmetic (`+`, `-`, `*`, `/`).
- Use a map or similar structure to track computed cells and avoid redundant calculations.

### 2. Errors to Check For
- **Invalid References**: Ensure all referenced cells exist within the bounds of the sheet.
- **Circular Dependencies**: Detect and prevent infinite loops caused by circular formula references.
- **Invalid Formulas**: Check for malformed formulas and unsupported operations.
- **Division by Zero**: Gracefully handle cases where formulas involve division by zero.

### 3. Potential Ways Users Might Break the Code
- Providing malformed input with incorrect syntax (e.g., `==5+A1`, `A2+` without `=`).
- Using undefined cell references (e.g., `A4` when only `A1-A3` exist).
- Creating circular references that lead to infinite recursion.
- Including non-numeric operations or unsupported symbols in formulas.

---

## Tools and Technologies
- **Java**: Programming language for implementing the solution.
- **Spring Boot**: Framework for building REST APIs.
- **Apache Commons CSV**: Library for reading and writing CSV files.
- **JUnit**: For unit testing the functionality.
- **Maven**: Dependency management and build automation.

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Test the endpoints or CSV processing:
   ```bash
   mvn test
   ```

---

## License
This project is licensed under the MIT License. For more details, see the LICENSE file.

---

## Author
Developed by Ayush Kumar.

