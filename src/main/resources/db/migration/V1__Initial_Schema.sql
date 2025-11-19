-- Select the database to use
USE journal_app_db;

-- 1. Create the Users table
CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Create the Journals table
CREATE TABLE journals (
    journal_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    entry_date DATE NOT NULL,
    mood VARCHAR(50),
    weather VARCHAR(50),
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Add a Foreign Key constraint to link entries to a specific user
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,

    -- Ensure a user can only have one entry per day
    UNIQUE KEY unique_user_date (user_id, entry_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
