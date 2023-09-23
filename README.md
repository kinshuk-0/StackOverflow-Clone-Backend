# StackOverflow Clone APIs

## *System Design*

The system design for the Stack Overflow clone consists of a Spring Boot backend with a MySQL database

1. **Database Schema:**
    - Utilizes MySQL to store data with tables for users, questions, answers, and tags.
    - Employs foreign key relationships to establish associations between entities.

2. **API Endpoints:**
    - Provides RESTful endpoints for user authentication, question posting, editing, and marking answers as accepted.
    - Implements validation and error handling for robustness.

3. **Security:**
    - Utilizes Spring Security and JWT token for user authentication and authorization.
    - Hashes and salts passwords for security.

4. **Service Layer:**
    - Encapsulates business logic and coordinates data interactions between controllers and repositories.

5. **Repository Layer:**
    - Utilizes Spring Data JPA repositories for performing CRUD operations on entities.

6. **Data Transfer Objects (DTOs):**
    - Employs DTOs for clean data exchange between the frontend and backend.

### Schemas:
- #### User Table
| Column Name | Data Type        | Constraints                 |
|-------------|------------------|-----------------------------|
| user_id     | INT              | PRIMARY KEY, AUTO_INCREMENT |
| username    | VARCHAR(255)     | NOT NULL, UNIQUE            |
| password    | VARCHAR(255)     | NOT NULL                    |

- #### Questions Table
| Column Name     | Data Type    | Constraints                     |
|-----------------|--------------|---------------------------------|
| question_id     | INT          | PRIMARY KEY, AUTO_INCREMENT     |
| title           | VARCHAR(255) | NOT NULL                        |
| body            | TEXT         | NOT NULL                        |
| user_id         | INT          | FOREIGN KEY (users.user_id)     |
| accepted_answer | INT          | FOREIGN KEY (answers.answer_id) |

- #### Answer Table
| Column Name | Data Type        | Constraints                         |
|-------------|------------------|-------------------------------------|
| answer_id   | INT              | PRIMARY KEY, AUTO_INCREMENT         |
| body        | TEXT             | NOT NULL                            |
| user_id     | INT              | FOREIGN KEY (users.user_id)         |
| question_id | INT              | FOREIGN KEY (questions.question_id) |
|accepted     | Boolean          | DEFAULT FALSE                       |

- #### Tag Table
| Column Name | Data Type    | Constraints                         |
|-------------|--------------|-------------------------------------|
| question_id | INT          | FOREIGN KEY (questions.question_id) |
| tag         | VARCHAR(255) | NOT NULL                            |


## *APIs*

### User Signup API
```
const API_PATH   : '/signup'
const API_METHOD : 'POST'

export type request = {
    "username"   : string
    "password"   : string
} 

export type response = {
    "id"         : number
    "username"   : string
}
```

### User Authentication API
```
const API_PATH   : '/auth'
const API_METHOD : 'POST'

export type request = {
    "username"   : string
    "password"   : string
}

export type response = {
    "token"      : string
}
```

### Post Question API
```
const API_PATH   : '/question/post'
const API_METHOD : 'POST'

export type request = {
    "title"      : string
    "body"       : string
    "tags"       : string[]
    "userId"     : number
}

```

### Edit Existing Question API

```
const API_PATH   : '/question/edit'
const API_METHOD : 'PUT'

export type request = {
    "userId"        : number
    "questionId"    : number
    "title"         : string
    "body"          : string
}
```


### Post Answer API
```
const API_PATH   : '/answer/post'
const API_METHOD : 'POST'

export type request = {
    "body"          : string
    "questionId"    : number
    "userId"        : number
}

```




### Accept Answer API
```
const API_PATH   : '/answer/accept'
const API_METHOD : 'PUT'

export type request = {
    "answerId"   : string
    "questionId" : number
}

```
