CREATE TABLE books(
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    author VARCHAR(80) NOT NULL,
    languages VARCHAR(80) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)