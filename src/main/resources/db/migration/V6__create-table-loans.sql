CREATE TABLE loans (
    id UUID PRIMARY KEY,
    loan_date TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    return_date TIMESTAMP,
    status VARCHAR(255) NOT NULL,
    book_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
