-- Create users table for authentication
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- In a real app, this should be hashed
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL, -- Role can be 'ADMIN', 'STUDENT', 'FACULTY'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Insert sample users
INSERT INTO users (username, password, email, role) VALUES
                                                        ('admin_user', 'adminpassword', 'admin@example.com', 'ADMIN'),
                                                        ('johndoe', 'johnpassword', 'john.doe@example.com', 'STUDENT'),
                                                        ('janedoe', 'janepassword', 'jane.doe@example.com', 'STUDENT'),
                                                        ('professor_jones', 'professorpassword', 'prof.jones@example.com', 'FACULTY');

-- --------------------------------
-- COURSE SERVICE DATABASE (course_db)
-- --------------------------------

-- Create courses table
CREATE TABLE IF NOT EXISTS courses (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
    description TEXT,
    instructor_id INT NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (instructor_id) REFERENCES users(id) ON DELETE CASCADE
    );

-- Insert sample courses
-- Assuming that the instructor IDs refer to the faculty in the 'users' table
INSERT INTO courses (name, description, instructor_id) VALUES
                                                           ('Introduction to Java', 'This course covers the basics of Java programming including syntax, OOP, and libraries.', 4),  -- Taught by Professor Jones
                                                           ('Advanced Databases', 'A deep dive into relational databases, SQL optimization, and indexing strategies.', 4),  -- Taught by Professor Jones
                                                           ('Machine Learning 101', 'Introduction to machine learning concepts, including supervised and unsupervised learning.', 4),  -- Taught by Professor Jones
                                                           ('Web Development with React', 'Learn to build modern web applications using React.js and Node.js.', 4),  -- Taught by Professor Jones
                                                           ('Introduction to Python', 'Learn Python programming basics for data analysis, web development.', 4)