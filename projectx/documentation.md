# ProjectX - Attendance System API

A Spring Boot 3 application with:
- User Registration & Login (JWT Authentication)
- Attendance Tracking (Time-in / Time-out)
- Image Upload (Local Storage)
- Role-based users (USER / ADMIN)

---

# Tech Stack

- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Lombok
- Multipart File Upload

---

# Base URL

```
http://localhost:8080/api
```

---

# Authentication

JWT is used for securing endpoints.

### Header Format

```
Authorization: Bearer <JWT_TOKEN>
```

---

# USER MODULE

## Register User

### Endpoint
```
POST /api/auth/register
```

### Request Body (JSON)

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@gmail.com",
  "password": "123456",
  "birthdate": "2026-06-16",
  "role": "USER"
}
```

### Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@gmail.com",
  "birthdate": "2026-06-16",
  "role": "USER"
}
```

---

## Login User

### Endpoint
```
POST /api/auth/login
```

### Request Body

```json
{
  "email": "john@gmail.com",
  "password": "123456"
}
```

### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "email": "john@gmail.com",
  "role": "USER"
}
```

---

# ATTENDANCE MODULE

##  Time In / Time Out

### Endpoint
```
POST /api/attendances
```

### Headers

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: multipart/form-data
```

---

### Request Body (form-data)

| Key       | Type | Example   |
|----------|------|-----------|
| latitude | Text | 14.5995   |
| longitude| Text | 120.9842  |
| picture  | File | selfie.jpg|

---

### Response

```json
{
  "id": 1,
  "dateTime": "2026-06-16T10:30:00",
  "type": "IN",
  "latitude": 14.5995,
  "longitude": 120.9842,
  "picture": "uploads/attendance/uuid_selfie.jpg",
  "user": {
    "id": 1,
    "email": "john@gmail.com"
  }
}
```

---

## Get All Attendance
```
GET /api/attendances
```

---

## Get Attendance by ID
```
GET /api/attendances/{id}
```

---

## Get Attendance by User
```
GET /api/attendances/user/{userId}
```

---

## Delete Attendance
```
DELETE /api/attendances/{id}
```

---

# FILE UPLOAD INFO

## Upload Location
```
uploads/attendance/
```

## Example File
```
uploads/attendance/uuid_selfie.jpg
```

---

## Access Image
```
http://localhost:8080/uploads/attendance/filename.jpg
```

---

# SECURITY FLOW

```
Register → Login → JWT Token → Access Attendance API
```

---

#  NOTES

- Password is encrypted using BCrypt
- JWT is required for attendance API
- Images stored locally (not DB)
- User is auto-linked via authentication

---

# FUTURE IMPROVEMENTS

- AWS S3 image storage
- Time-in/out validation rules
- Prevent duplicate attendance per day
- Admin dashboard
- Geolocation restrictions

---
ProjectX Attendance System - Spring Boot 3 + Java 21